package ru.ancap.framework.artifex.implementation.command.center;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.bukkit.command.CommandExecutor;
import org.jetbrains.annotations.Nullable;
import ru.ancap.commons.map.GuaranteedMap;
import ru.ancap.framework.artifex.Artifex;
import ru.ancap.framework.command.api.commands.object.dispatched.LeveledCommand;
import ru.ancap.framework.command.api.commands.object.event.CommandDispatch;
import ru.ancap.framework.command.api.commands.object.event.CommandWrite;
import ru.ancap.framework.command.api.commands.object.executor.CommandOperator;
import ru.ancap.framework.command.api.commands.operator.delegate.subcommand.rule.delegate.operate.OperateRule;
import ru.ancap.framework.communicate.communicator.Communicator;
import ru.ancap.framework.communicate.message.CallableMessage;
import ru.ancap.framework.language.additional.LAPIMessage;
import ru.ancap.framework.plugin.api.AncapBukkit;
import ru.ancap.framework.plugin.api.AncapPlugin;
import ru.ancap.framework.plugin.api.commands.CommandCenter;
import ru.ancap.framework.plugin.api.commands.CommandData;
import ru.ancap.framework.plugin.api.commands.CommandHandleState;
import ru.ancap.framework.plugin.api.exception.CommandAlreadyRegisteredException;
import ru.ancap.framework.plugin.api.exception.CommandNotRegisteredException;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@RequiredArgsConstructor
@ToString @EqualsAndHashCode
public class AsyncCommandCenter implements CommandCenter, CommandOperator, OperateRule {
    
    private final Map<String /*command*/, String /*id*/       > redirectMap     = new ConcurrentHashMap<>();
    private final Map<String /*id*/,      CommandData         > commandDatas    = new ConcurrentHashMap<>();
    private final Map<AncapPlugin,        Set<String /*id*/ > > pluginRegisters = new GuaranteedMap<>(HashSet::new);
    
    private final CommandExecutor proxy;

    @Override
    public void initialize(AncapPlugin plugin) {
        for (String commandName : plugin.getSettings().getCommandList()) this.register(
            commandName,
            plugin.getSettings().getAliasesList(commandName),
            new CommandHandleState(
                CommandOperator.EMPTY,
                plugin
            )
        );
    }
    
    @Override
    public void register(String id, List<String> sources, CommandHandleState state) {
        CommandData previous = this.commandDatas.get(id);
        if (previous != null) throw new CommandAlreadyRegisteredException(id);
        
        for (String redirect : sources) this.redirectMap.put(redirect, id);
        CommandData data = new CommandData(id, sources, state);
        this.commandDatas.put(id, data);
        
        AncapPlugin owner = state.owner() != null ? state.owner() : Artifex.PLUGIN();
        this.pluginRegisters.get(owner).add(id);
        
        AncapBukkit.registerCommandExecutor(id, owner, sources, this.proxy);
    }

    @Override
    public void unregister(String id) {
        CommandData data = this.commandDatas.get(id);
        if (data == null) throw new CommandNotRegisteredException(id);
        for (String command : data.sources()) this.redirectMap.remove(command);
        if (data.handleState().owner() != null) this.pluginRegisters.get(data.handleState().owner()).remove(id);
        
        AncapBukkit.unregisterCommandExecutor(id);
    }

    @Override
    public void setExecutor(String id, CommandOperator operator) throws CommandNotRegisteredException {
        CommandData data = this.commandDatas.get(id);
        if (data == null) throw new CommandNotRegisteredException(id);
        this.commandDatas.put(id, data.withHandleState(data.handleState().withOperator(operator)));
    }

    @Override
    public @Nullable CommandData findDataOf(String id) {
        return this.commandDatas.get(id);
    }

    @Override
    public Set<String> findRegisteredCommandsOf(AncapPlugin plugin) {
        return this.pluginRegisters.get(plugin);
    }

    @Override
    public void on(CommandDispatch dispatch) {
        this.operate(
            dispatch.command(),
            commandForm -> commandForm.commandOperator.on(new CommandDispatch(
                dispatch.source(),
                commandForm.command
            )),
            message -> Communicator.of(dispatch.source().sender()).message(message)
        );
    }

    @Override
    public void on(CommandWrite write) {
        this.operate(
            write.line(),
            commandForm -> commandForm.commandOperator.on(new CommandWrite(
                write.speaker(),
                commandForm.command
            )),
            message -> Communicator.of(write.speaker().source().sender()).message(message)
        );
    }

    private void operate(LeveledCommand command, Consumer<CommandForm> commandFormConsumer, Consumer<CallableMessage> fallback) {
        String key = command.nextArgument();
        LeveledCommand finalCommand = command.withoutArgument();
        new Thread(() -> {
            try {
                String id = this.redirectMap.get(key);
                CommandOperator rule = this.commandDatas.get(id).handleState().operator();
                commandFormConsumer.accept(new CommandForm(
                    finalCommand,
                    rule
                ));
            } catch (Throwable throwable) {
                fallback.accept(new LAPIMessage(Artifex.class, "command.api.error.internal"));
                throwable.printStackTrace();
            }
        }).start();

    }

    @Override
    public boolean isOperate(LeveledCommand command) {
        if (command.isRaw()) return false;
        return this.redirectMap.containsKey(command.nextArgument());
    }
    
    private record CommandForm(LeveledCommand command, CommandOperator commandOperator) {}
    
}
