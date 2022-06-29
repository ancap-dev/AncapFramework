package ru.ancap.framework.api.command.commands;

import ru.ancap.framework.api.command.commands.command.dispatched.DispatchedCommand;
import ru.ancap.framework.api.command.commands.command.executor.CommandExecutor;

import java.util.List;

public class CommandTarget implements CommandExecutor {

    private final CommandExecutor executor;

    public CommandTarget(CommandExecutor executor) {
        this.executor = executor;
    }

    public CommandTarget(CommandTarget target) {
        this(target.executor);
    }

    protected CommandExecutor getExecutor() {
        return this.executor;
    }

    @Override
    public void on(DispatchedCommand command) {
        this.executor.on(command);
    }

    @Override
    public List<String> getTabCompletionsFor(DispatchedCommand command) {
        return this.executor.getTabCompletionsFor(command);
    }
}
