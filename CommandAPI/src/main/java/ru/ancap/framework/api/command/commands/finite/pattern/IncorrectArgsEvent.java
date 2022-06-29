package ru.ancap.framework.api.command.commands.finite.pattern;

import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import ru.ancap.framework.api.event.CommandEvent;

@Getter
public class IncorrectArgsEvent extends CommandEvent {

    private String argument;

    public IncorrectArgsEvent(CommandSender sender, String argument) {
        super(sender);
        this.argument = argument;
    }

    private static final HandlerList handlers = new HandlerList();
    public @NotNull HandlerList getHandlers() {return handlers;}
    public static @NotNull HandlerList getHandlerList() {return handlers;}
}
