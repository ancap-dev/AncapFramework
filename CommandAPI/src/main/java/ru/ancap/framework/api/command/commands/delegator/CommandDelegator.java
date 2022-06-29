package ru.ancap.framework.api.command.commands.delegator;

import ru.ancap.framework.api.command.commands.command.dispatched.DispatchedCommand;
import ru.ancap.framework.api.command.commands.command.executor.CommandExecutor;
import ru.ancap.framework.api.command.commands.delegator.settings.ClassicDelegatorSettings;
import ru.ancap.framework.api.command.commands.delegator.settings.DelegatorSettings;
import ru.ancap.framework.api.command.commands.delegator.subcommand.rule.CommandDelegateRule;
import ru.ancap.framework.api.command.commands.delegator.subcommand.rule.provide.CommandProvidePattern;

import java.util.ArrayList;
import java.util.List;

public class CommandDelegator implements CommandExecutor {

    private final CommandProvidePattern defaultRule;
    private final List<CommandDelegateRule> rules;

    private CommandDelegator(CommandProvidePattern defaultRule, List<CommandDelegateRule> provideRules) {
        this.defaultRule = defaultRule;
        this.rules = provideRules;
    }

    public CommandDelegator(DelegatorSettings settings, CommandDelegateRule... provideRules) {
        this(
                settings.getDefaultRule(),
                List.of(provideRules)
        );
    }

    public CommandDelegator(CommandDelegateRule... provideRules) {
        this(
                new ClassicDelegatorSettings(),
                provideRules
        );
    }

    @Override
    public void on(DispatchedCommand command) {
        for (CommandDelegateRule rule : this.rules) {
            if (rule.isOperate(command)) {
                rule.delegated().on(rule.getCommandProvideToExecutor(command));
            }
        }
        this.defaultRule.delegated().on(this.defaultRule.getCommandProvideToExecutor(command));
    }

    @Override
    public List<String> getTabCompletionsFor(DispatchedCommand command) {
        List<String> candidates = new ArrayList<>();
        for (CommandDelegateRule rule : this.rules) {
            candidates.addAll(rule.candidates());
        }
        return candidates;
    }

}
