package ru.ancap.framework.api.command.commands.delegator.subcommand.rule.delegate.operate;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import ru.ancap.framework.api.command.commands.command.dispatched.DispatchedCommand;
import ru.ancap.framework.api.command.commands.command.dispatched.exception.NoNextArgumentException;

public class ArgumentsOperateRule implements OperateRule {

    private final String key;

    public ArgumentsOperateRule(@NotNull String key) {
        this.key = key;
    }

    @Override
    public boolean isOperate(DispatchedCommand command) {
        return new ArgumentsOperatedCommand(command).hasArgument(key);
    }

    @AllArgsConstructor
    private static class ArgumentsOperatedCommand {

        private final DispatchedCommand command;

        boolean hasArgument(String key) {
            try {
                return command.nextArgument().equalsIgnoreCase(key);
            } catch (NoNextArgumentException e) {
                return false;
            }
        }

    }
}
