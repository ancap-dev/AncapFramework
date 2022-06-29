package ru.ancap.framework.api.event.classic;

import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import ru.ancap.framework.api.event.CommandEvent;

@Getter
public class NotEnoughArgsEvent extends CommandEvent {

    private final int argumentsLack;

    public NotEnoughArgsEvent(CommandSender sender, int argumentsLack) {
        super(sender);
        this.argumentsLack = argumentsLack;
    }

    private static final HandlerList handlers = new HandlerList();
    public @NotNull HandlerList getHandlers() {return handlers;}
    public static @NotNull HandlerList getHandlerList() {return handlers;}
}
