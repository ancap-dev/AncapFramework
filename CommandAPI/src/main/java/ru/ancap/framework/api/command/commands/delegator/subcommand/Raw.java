package ru.ancap.framework.api.command.commands.delegator.subcommand;

import ru.ancap.framework.api.command.commands.command.dispatched.DispatchedCommand;
import ru.ancap.framework.api.command.commands.command.executor.CommandExecutor;
import ru.ancap.framework.api.command.commands.delegator.subcommand.rule.CommandDelegateRule;
import ru.ancap.framework.api.command.commands.finite.FiniteCommandTarget;
import ru.ancap.framework.api.command.commands.finite.pattern.ArgumentCutter;

public class Raw implements CommandDelegateRule {

    private final CommandExecutor delegated;

    public Raw(CommandExecutor delegated) {
        this.delegated = delegated;
    }

    public Raw(ArgumentCutter.SenderEventProvider provider) {
        this.delegated = new FiniteCommandTarget(
                new ArgumentCutter(provider)
        );
    }

    @Override
    public boolean isOperate(DispatchedCommand command) {
        return command.isRaw();
    }

    @Override
    public CommandExecutor delegated() {
        return this.delegated;
    }
}
