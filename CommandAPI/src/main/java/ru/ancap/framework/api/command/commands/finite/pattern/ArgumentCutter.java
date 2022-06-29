package ru.ancap.framework.api.command.commands.finite.pattern;

import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import ru.ancap.framework.api.command.commands.command.dispatched.DispatchedCommand;

public class ArgumentCutter implements CommandEventPattern {

    private final SenderEventProvider provider;

    public ArgumentCutter(SenderEventProvider provider) {
        this.provider = provider;
    }

    @Override
    public Event patternalize(DispatchedCommand command) {
        return this.provider.event(command.getSender());
    }

    @FunctionalInterface
    public interface SenderEventProvider {
        Event event(CommandSender sender);
    }

}
