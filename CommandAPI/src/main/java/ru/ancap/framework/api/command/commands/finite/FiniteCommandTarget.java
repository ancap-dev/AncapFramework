package ru.ancap.framework.api.command.commands.finite;

import ru.ancap.framework.api.command.commands.command.dispatched.DispatchedCommand;
import ru.ancap.framework.api.command.commands.command.executor.CommandExecutor;
import ru.ancap.framework.api.command.commands.command.executor.TabProvider;
import ru.ancap.framework.api.command.commands.finite.pattern.CommandEventPattern;

import java.util.ArrayList;
import java.util.List;


public class FiniteCommandTarget implements CommandExecutor {

    private final TabProvider provider;
    private final CommandEventPattern patterner;

    public FiniteCommandTarget(CommandEventPattern patterner, TabProvider provider) {
        this.patterner = patterner;
        this.provider = provider;
    }

    public FiniteCommandTarget(CommandEventPattern patterner) {
        this(patterner, command -> new ArrayList<>());
    }

    @Override
    public void on(DispatchedCommand command) {
        patterner.patternalize(command).callEvent();
    }

    @Override
    public List<String> getTabCompletionsFor(DispatchedCommand command) {
        return provider.provideFor(command);
    }
}
