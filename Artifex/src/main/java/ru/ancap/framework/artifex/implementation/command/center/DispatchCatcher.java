package ru.ancap.framework.artifex.implementation.command.center;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;
import ru.ancap.framework.artifex.Artifex;
import ru.ancap.framework.artifex.implementation.command.center.util.ArgumentSplitter;
import ru.ancap.framework.artifex.implementation.command.event.ProxiedCommandEvent;
import ru.ancap.framework.artifex.implementation.command.object.SenderSource;
import ru.ancap.framework.command.api.commands.object.conversation.CommandSource;
import ru.ancap.framework.command.api.commands.object.dispatched.LeveledCommand;
import ru.ancap.framework.command.api.commands.object.dispatched.TextCommand;
import ru.ancap.framework.command.api.commands.object.event.CommandDispatch;
import ru.ancap.framework.command.api.commands.object.executor.CommandOperator;
import ru.ancap.framework.command.api.commands.operator.delegate.subcommand.rule.delegate.operate.OperateRule;
import ru.ancap.framework.communicate.communicator.Communicator;
import ru.ancap.framework.communicate.modifier.Placeholder;
import ru.ancap.framework.language.additional.LAPIMessage;
import ru.ancap.framework.plugin.api.Ancap;

import java.util.List;
import java.util.function.Consumer;

@ToString @EqualsAndHashCode
@Accessors(fluent = true) @Getter(AccessLevel.PRIVATE)
public class DispatchCatcher implements Listener {
    
    private final Ancap ancap;
    private final CommandOperator global;
    private final OperateRule scope;
    
    DispatchCatcher(Ancap ancap, CommandOperator global, OperateRule scope) {
        this.ancap = ancap;
        this.global = global;
        this.scope = scope;
    }
    
    @EventHandler
    public void on(PlayerCommandPreprocessEvent event) {
        this.operateInterceptableDispatch(
            Interceptable.ofEvent(event),
            new SenderSource(event.getPlayer()),
            event.getMessage(),
            this::operateInterceptableDispatch
        );
    }
    
    @EventHandler(priority = EventPriority.LOW, ignoreCancelled = false)
    public void on(ServerCommandEvent event) {
        this.operateInterceptableDispatch(
            Interceptable.ofEvent(event),
            new SenderSource(event.getSender()),
            event.getCommand(),
            this::operateInterceptableDispatch
        );
    }
    
    @EventHandler
    public void on(ProxiedCommandEvent event) {
        this.operateInterceptableDispatch(
            Interceptable.ofEvent(event),
            new SenderSource(event.sender()),
            event.command(),
            this::operateInterceptableDispatch
        );
    }
    
    private String commandLineWithoutSlash(String commandLine) {
        if (commandLine.startsWith("/")) commandLine = commandLine.substring(1);
        return commandLine;
    }
    
    private void notifyAboutServerCommand(CommandSender sender, String command) {
        Communicator.of(Bukkit.getConsoleSender()).message(new LAPIMessage(
            Artifex.class, "command.api.info.issued-server-command",
            new Placeholder("source", sender.getName()),
            new Placeholder("command", command)
        ));
    }
    
    private void operateInterceptableDispatch(InterceptableCommandForm form) {
        this.global.on(new CommandDispatch(form.source, form.command));
        form.interceptable.intercept();
    }
    
    private void operateInterceptableDispatch(Interceptable interceptable, CommandSource source, String sent, Consumer<InterceptableCommandForm> consumer) {
        if (interceptable.intercepted()) return;
        sent = this.commandLineWithoutSlash(sent);
        TextCommand command = this.from(source, sent);
        if (!this.scope().isOperate(command)) return;
        consumer.accept(new InterceptableCommandForm(interceptable, source, command));
    }
    
    private TextCommand from(CommandSource source, String message) {
        return this.from(new RawForm(source, message));
    }
    
    private TextCommand from(RawForm form) {
        return this.from(new Form(form.source, ArgumentSplitter.split(form.command)));
    }
    
    private TextCommand from(Form form) {
        return new TextCommand(form.arguments);
    }
    
    private record RawForm(CommandSource source, String command) { }
    private record Form(CommandSource source, List<String> arguments) { }
    private record InterceptableCommandForm(Interceptable interceptable, CommandSource source, LeveledCommand command) { }
    
    public interface Interceptable {
        
        static Interceptable ofEvent(Cancellable event) {
            
            return new Interceptable() {
                @Override
                public boolean intercepted() {
                    return event.isCancelled();
                }
                
                @Override
                public void intercept() {
                    event.setCancelled(true);
                }
            };
            
        }
        
        boolean intercepted();
        void intercept();
        
    }
    
}