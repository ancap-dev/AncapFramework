package ru.ancap.framework.api.command.commands.delegator.subcommand.rule;

import ru.ancap.framework.api.command.commands.delegator.subcommand.rule.delegate.DelegatePattern;
import ru.ancap.framework.api.command.commands.delegator.subcommand.rule.provide.CommandProvidePattern;

import java.util.ArrayList;
import java.util.List;

public interface CommandDelegateRule extends DelegatePattern, CommandProvidePattern {

    default List<String> candidates() {
        return new ArrayList<>();
    }

}
