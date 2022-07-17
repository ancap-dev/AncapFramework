package ru.ancap.framework.api.command.commands;

import lombok.experimental.Delegate;
import ru.ancap.framework.api.command.commands.command.executor.CommandOperator;

public class CommandTarget implements CommandOperator {

    @Delegate
    private final CommandOperator executor;

    public CommandTarget(CommandOperator executor) {
        this.executor = executor;
    }

    public CommandTarget(CommandTarget target) {
        this(target.executor);
    }

    protected CommandOperator getExecutor() {
        return this.executor;
    }
}
