package ru.ancap.framework.api.event.classic;

import lombok.Getter;
import org.bukkit.command.CommandSender;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;
import ru.ancap.framework.api.event.CommandEvent;

@Getter
public class UnknownCommandEvent extends CommandEvent {

    private String unknownValue;

    public UnknownCommandEvent(@NotNull CommandSender sender, @NotNull String unknownValue) {
        super(sender);
        this.unknownValue = unknownValue;
    }

    private static final HandlerList handlers = new HandlerList();
    public @NotNull HandlerList getHandlers() {return handlers;}
    public static @NotNull HandlerList getHandlerList() {return handlers;}

}
