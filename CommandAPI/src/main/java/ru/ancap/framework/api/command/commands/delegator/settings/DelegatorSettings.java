package ru.ancap.framework.api.command.commands.delegator.settings;

import ru.ancap.framework.api.command.commands.delegator.subcommand.rule.provide.CommandProvidePattern;

public interface DelegatorSettings {

    CommandProvidePattern getDefaultRule();

}
