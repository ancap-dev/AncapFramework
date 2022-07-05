package ru.ancap.framework.api.event.events.wrapper;

import org.bukkit.Location;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Calling, when something is exploding.
 */

public class ExplodeEvent extends AncapWrapperCancellableEvent implements Cancellable {

    public static final HandlerList handlers = new HandlerList();

    private final @NotNull Location location;

    public ExplodeEvent(@NotNull Cancellable event, @NotNull Location location) {
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
