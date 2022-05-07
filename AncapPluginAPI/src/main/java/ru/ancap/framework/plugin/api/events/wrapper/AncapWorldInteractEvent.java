package ru.ancap.framework.plugin.api.events.wrapper;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Calling, when some player interacts with the world
 */

public class AncapWorldInteractEvent extends AncapWrapperPlayerEvent implements Cancellable {

    public static final HandlerList handlers = new HandlerList();

    private final @NotNull Location loc;

    public AncapWorldInteractEvent(@NotNull Cancellable event, @NotNull Player player, @NotNull Location loc) {
        super(event, player);
        this.loc = loc;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @NotNull
    public Location getLocation() {
        return this.loc;
    }
}
