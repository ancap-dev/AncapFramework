package ru.ancap.framework.api.command.commands.delegator.subcommand;

import ru.ancap.framework.api.command.commands.command.dispatched.LeveledCommand;
import ru.ancap.framework.api.command.commands.command.executor.CommandOperator;
import ru.ancap.framework.api.command.commands.delegator.subcommand.rule.CommandDelegateRule;
import ru.ancap.framework.api.command.commands.finite.FiniteCommandTarget;
import ru.ancap.framework.api.command.commands.finite.pattern.ArgumentCutter;

/**
 * It's a command delegate rule that delegates to a command executor if the command is raw
 */
public class Raw implements CommandDelegateRule {

    private final CommandOperator delegated;

    public Raw(CommandOperator delegated) {
        this.delegated = delegated;
    }

    public Raw(ArgumentCutter.SenderEventProvider provider) {
        this.delegated = new FiniteCommandTarget(
                new ArgumentCutter(provider)
        );
    }

    @Override
    public boolean isOperate(LeveledCommand command) {
        return command.isRaw();
    }

    @Override
    public CommandOperator delegated() {
        return this.delegated;
    }
}
