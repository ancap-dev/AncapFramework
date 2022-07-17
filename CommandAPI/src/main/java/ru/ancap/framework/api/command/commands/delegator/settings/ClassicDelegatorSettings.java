package ru.ancap.framework.api.command.commands.delegator.settings;

import lombok.Getter;
import ru.ancap.framework.api.command.commands.command.executor.CommandOperator;
import ru.ancap.framework.api.command.commands.delegator.subcommand.rule.provide.CommandProvidePattern;
import ru.ancap.framework.api.command.commands.finite.FiniteCommandTarget;
import ru.ancap.framework.api.command.commands.finite.pattern.SingleArgumenter;
import ru.ancap.framework.api.event.classic.UnknownCommandEvent;

/**
 * It's a delegator settings class that uses the `UnknownCommandEvent` as the default rule
 */
@Getter
public class ClassicDelegatorSettings implements DelegatorSettings {

    private final CommandProvidePattern spareRule;

    public ClassicDelegatorSettings() {
        this.spareRule = () -> new FiniteCommandTarget(
                new SingleArgumenter(UnknownCommandEvent::new)
        );
    }

    @Override
    public CommandProvidePattern getDefaultRule() {
        return spareRule;
    }

    public static class UnknownSubCommand implements CommandProvidePattern {

        private final CommandOperator executor;

        public UnknownSubCommand(CommandOperator delegated) {
            this.executor = delegated;
        }

        @Override
        public CommandOperator delegated() {
            return this.executor;
        }

    }
}
