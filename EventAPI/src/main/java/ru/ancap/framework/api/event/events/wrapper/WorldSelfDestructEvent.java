package ru.ancap.framework.api.event.events.wrapper;

import org.bukkit.Location;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Calling, when world trying to destruct itself (maybe due to player's influence). For example, piston extracts
 * or entity attacks other entity.
 */

public class WorldSelfDestructEvent extends AncapWrapperCancellableEvent implements Cancellable {

    public static final HandlerList handlers = new HandlerList();

    private final @NotNull Location destructionPosition;
    private final @NotNull Location destructorPosition;

    public WorldSelfDestructEvent(@NotNull Cancellable event, @NotNull Location destructionPosition, @NotNull Location destructorPosition) {
        super(event);
        this.destructionPosition = destructionPosition;
        this.destructorPosition = destructorPosition;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    @NotNull
    public Location getDestructionPosition() {
        return this.destructionPosition;
    }

    @NotNull
    public Location getDestructorPosition() {
        return this.destructorPosition;
    }

}
