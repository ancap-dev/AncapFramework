package ru.ancap.framework.api.command.commands.finite.pattern;

import org.bukkit.event.Event;
import ru.ancap.framework.api.command.commands.command.dispatched.DispatchedCommand;

public interface CommandEventPattern {

    Event patternalize(DispatchedCommand command);

}
