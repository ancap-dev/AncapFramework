package ru.ancap.framework.plugin.api.events.wrapper;

import org.bukkit.Location;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Calling, when world trying to destruct itself (maybe due to player's influence). For example, piston extracts
 * or entity attacks other entity.
 */

public class AncapWorldSelfDestructEvent extends AncapEvent implements Cancellable {

    public static final HandlerList handlers = new HandlerList();

    private final @NotNull Cancellable event;
    private final @NotNull Location destructionPosition;
    private final @NotNull Location destructorPosition;

    public AncapWorldSelfDestructEvent(@NotNull Cancellable e, @NotNull Location destructionPosition, @NotNull Location destructorPosition) {
        this.event = e;
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

    @NotNull
    public Cancellable getBukkitEvent() {
        return this.event;
    }

    @Override
    public boolean isCancelled() {
        return event.isCancelled();
    }

    @Override
    public void setCancelled(boolean b) {
        event.setCancelled(b);
    }

}
