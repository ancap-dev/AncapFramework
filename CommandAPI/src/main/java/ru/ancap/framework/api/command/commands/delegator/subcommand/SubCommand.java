package ru.ancap.framework.api.command.commands.delegator.subcommand;

import ru.ancap.framework.api.command.commands.command.dispatched.DispatchedCommand;
import ru.ancap.framework.api.command.commands.command.executor.CommandExecutor;
import ru.ancap.framework.api.command.commands.delegator.subcommand.rule.CommandDelegateRule;
import ru.ancap.framework.api.command.commands.delegator.subcommand.rule.delegate.DelegatePattern;
import ru.ancap.framework.api.command.commands.delegator.subcommand.rule.delegate.operate.OperateRule;

import java.util.List;

public class SubCommand implements CommandDelegateRule {

    private final OperateRule operateRule;
    private final List<String> candidates;
    private final CommandExecutor delegated;

    public SubCommand(OperateRule operateRule, List<String> candidates, CommandExecutor executor) {
        this.operateRule = operateRule;
        this.candidates = candidates;
        this.delegated = executor;
    }

    public SubCommand(DelegatePattern delegatePattern, CommandExecutor executor) {
        this(delegatePattern, delegatePattern.candidates(), executor);
    }

    @Override
    public boolean isOperate(DispatchedCommand command) {
        return this.operateRule.isOperate(command);
    }

    @Override
    public CommandExecutor delegated() {
        return this.delegated;
    }

    @Override
    public List<String> candidates() {
        return this.candidates;
    }

    @Override
    public DispatchedCommand getCommandProvideToExecutor(DispatchedCommand command) {
        return command.withoutArgument();
    }
}
