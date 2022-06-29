package ru.ancap.framework.api.command.commands.delegator.settings;

import lombok.Getter;
import ru.ancap.framework.api.command.commands.command.dispatched.DispatchedCommand;
import ru.ancap.framework.api.command.commands.command.dispatched.exception.NoNextArgumentException;
import ru.ancap.framework.api.command.commands.command.executor.CommandExecutor;
import ru.ancap.framework.api.command.commands.delegator.subcommand.rule.provide.CommandProvidePattern;
import ru.ancap.framework.api.command.commands.finite.FiniteCommandTarget;
import ru.ancap.framework.api.event.classic.UnknownCommandEvent;

@Getter
public class ClassicDelegatorSettings implements DelegatorSettings {

    private final CommandProvidePattern spareRule;

    public ClassicDelegatorSettings() {
        this.spareRule = () -> new FiniteCommandTarget(
                command -> {
                    try {
                        return new UnknownCommandEvent(command.getSender(), command.nextArgument());
                    } catch (NoNextArgumentException e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

    @Override
    public CommandProvidePattern getDefaultRule() {
        return spareRule;
    }

    public static class UnknownSubCommand implements CommandProvidePattern {

        private final CommandExecutor executor;

        public UnknownSubCommand(CommandExecutor delegated) {
            this.executor = delegated;
        }

        @Override
        public CommandExecutor delegated() {
            return this.executor;
        }

        @Override
        public DispatchedCommand getCommandProvideToExecutor(DispatchedCommand command) {
            return command;
        }

    }
}
