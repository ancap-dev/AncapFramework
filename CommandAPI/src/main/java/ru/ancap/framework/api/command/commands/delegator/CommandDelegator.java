package ru.ancap.framework.api.command.commands.delegator;

import ru.ancap.framework.api.command.commands.command.dispatched.LeveledCommand;
import ru.ancap.framework.api.command.commands.command.event.CommandDispatch;
import ru.ancap.framework.api.command.commands.command.event.CommandWrite;
import ru.ancap.framework.api.command.commands.command.executor.CommandOperator;
import ru.ancap.framework.api.command.commands.delegator.settings.ClassicDelegatorSettings;
import ru.ancap.framework.api.command.commands.delegator.settings.DelegatorSettings;
import ru.ancap.framework.api.command.commands.delegator.subcommand.rule.CommandDelegateRule;
import ru.ancap.framework.api.command.commands.delegator.subcommand.rule.provide.CommandProvidePattern;

import java.util.ArrayList;
import java.util.List;

/**
 * `CommandDelegator` is a `CommandExecutor` that delegates commands to other `CommandExecutor`s based on a set of rules
 */
public class CommandDelegator implements CommandOperator {

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
    public void on(CommandDispatch dispatch) {
        CommandProvidePattern pattern = this.ruleFrom(dispatch.dispatched());
        pattern.delegated().on(
                new CommandDispatch(
                        pattern.convert(dispatch.sender()),
                        pattern.convert(dispatch.dispatched())
                )
        );
    }

    @Override
    public void on(CommandWrite write) {
        if (write.getWritten().isRaw()) {
            List<String> candidates = new ArrayList<>();
            for (CommandDelegateRule rule : this.rules) {
                candidates.addAll(rule.candidates());
            }
            write.getSpeaker().sendTabs(candidates);
            return;
        }
        CommandProvidePattern pattern = this.ruleFrom(write.getWritten());
        pattern.delegated().on(
                new CommandWrite(
                        write.getSpeaker(),
                        pattern.convert(
                                write.getWritten()
                        )
                )
        );
    }

    private CommandProvidePattern ruleFrom(LeveledCommand command) {
        for (CommandDelegateRule rule : this.rules) {
            if (rule.isOperate(command)) {
                return rule;
            }
        }
        return defaultRule;
    }

}
