package ru.ancap.framework.api.command.commands.delegator.subcommand;

import ru.ancap.framework.api.command.commands.command.dispatched.LeveledCommand;
import ru.ancap.framework.api.command.commands.command.executor.CommandOperator;
import ru.ancap.framework.api.command.commands.delegator.subcommand.rule.CommandDelegateRule;
import ru.ancap.framework.api.command.commands.delegator.subcommand.rule.delegate.DelegatePattern;
import ru.ancap.framework.api.command.commands.delegator.subcommand.rule.delegate.operate.OperateRule;

import java.util.List;

public class SubCommand implements CommandDelegateRule {

    private final OperateRule operateRule;
    private final List<String> candidates;
    private final CommandOperator delegated;

    public SubCommand(OperateRule operateRule, List<String> candidates, CommandOperator executor) {
        this.operateRule = operateRule;
        this.candidates = candidates;
        this.delegated = executor;
    }

    public SubCommand(DelegatePattern delegatePattern, CommandOperator executor) {
        this(delegatePattern, delegatePattern.candidates(), executor);
    }

    @Override
    public boolean isOperate(LeveledCommand command) {
        return this.operateRule.isOperate(command);
    }

    @Override
    public CommandOperator delegated() {
        return this.delegated;
    }

    @Override
    public List<String> candidates() {
        return this.candidates;
    }

    @Override
    public LeveledCommand convert(LeveledCommand command) {
        return command.withoutArgument();
    }
}
