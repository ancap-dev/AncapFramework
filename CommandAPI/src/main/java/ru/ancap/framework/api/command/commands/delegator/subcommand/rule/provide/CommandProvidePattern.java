package ru.ancap.framework.api.command.commands.delegator.subcommand.rule.provide;

import ru.ancap.framework.api.command.commands.command.dispatched.DispatchedCommand;
import ru.ancap.framework.api.command.commands.command.executor.CommandExecutor;

public interface CommandProvidePattern {

    CommandExecutor delegated();

    default DispatchedCommand getCommandProvideToExecutor(DispatchedCommand command) {
        return command;
    }

}
