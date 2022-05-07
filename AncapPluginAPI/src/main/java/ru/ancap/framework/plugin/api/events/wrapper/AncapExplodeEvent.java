package ru.ancap.framework.plugin.api.events.wrapper;

import org.bukkit.Location;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Calling, when something is exploding.
 */

public class AncapExplodeEvent extends AncapWrapperCancellableEvent implements Cancellable {

    public static final HandlerList handlers = new HandlerList();

    private final @NotNull Location location;

    public AncapExplodeEvent(@NotNull Cancellable event, @NotNull Location location) {
        super(event);
        this.location = location;
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
        return this.location;
    }
}
