package ru.ancap.framework.artifex.implementation.communicator.message.clickable;

import lombok.RequiredArgsConstructor;
import ru.ancap.commons.map.GuaranteedMap;
import ru.ancap.framework.command.api.commands.object.event.CommandDispatch;
import ru.ancap.framework.command.api.commands.object.executor.CSCommandOperator;
import ru.ancap.framework.communicate.message.CallableMessage;
import ru.ancap.framework.communicate.message.Message;
import ru.ancap.framework.communicate.message.clickable.ActionMessageProvider;
import ru.ancap.framework.communicate.message.clickable.Click;
import ru.ancap.framework.communicate.message.clickable.ClickableMessage;
import ru.ancap.framework.communicate.modifier.Placeholder;
import ru.ancap.framework.plugin.api.commands.PluginCommandRegistrar;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class ActionProxy implements CSCommandOperator, ActionMessageProvider {

    private final Map<String, Consumer<Click>> proxyMap = new GuaranteedMap<>(() -> click -> {});
    private final String commandName;

    public void setup(PluginCommandRegistrar registrar) {
        ClickableMessage.provider = this;
        registrar.register(this.commandName, List.of(), this);
    }
    
    @Override
    public CallableMessage to(CallableMessage base, Consumer<Click> clickConsumer) {
        String actionId = generateActionID();
        this.proxyMap.put(actionId, clickConsumer);
        return new Message(
            "<click:run_command:/"+this.commandName+" "+actionId+">%AP_TEXT%</click>",
            new Placeholder("ap text", base)
        );
    }

    @Override
    public void on(CommandDispatch dispatch) {
        String actionID = dispatch.command().consumeArgument();
        this.proxyMap.get(actionID).accept(new Click(dispatch.source().sender()));
        this.proxyMap.remove(actionID);
    }

    private long actionCounter = 0;

    public synchronized String generateActionID() {
        try { return actionCounter+""; }
        finally { actionCounter++; }
    }
    
}
