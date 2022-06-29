package ru.ancap.framework.api.command.commands.command.executor;

import ru.ancap.framework.api.command.commands.command.dispatched.DispatchedCommand;

import java.util.List;

public interface TabProvider {

    List<String> provideFor(DispatchedCommand command);
}
