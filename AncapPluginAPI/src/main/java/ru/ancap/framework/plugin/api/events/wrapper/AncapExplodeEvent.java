package ru.ancap.framework.plugin.api.events.wrapper;

import org.bukkit.Location;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.jetbrains.annotations.NotNull;

/**
 * Calling, when something is exploding.
 */

public class AncapExplodeEvent extends AncapEvent implements Cancellable {

    public static final HandlerList handlers = new HandlerList();

    private final @NotNull Cancellable event;
    private final @NotNull Location location;

    public AncapExplodeEvent(@NotNull Cancellable event, @NotNull Location location) {
        this.location = location;
        this.event = event;
    }

    public AncapExplodeEvent(EntityExplodeEvent e) {
        this.event = e;
        this.location = e.getLocation();
    }

    public AncapExplodeEvent(BlockExplodeEvent e) {
        this.event = e;
        this.location = e.getBlock().getLocation();
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

    @NotNull
    public Cancellable getBukkitEvent() {
        return this.event;
    }

    @Override
    public boolean isCancelled() {
        return this.event.isCancelled();
    }

    @Override
    public void setCancelled(boolean b) {
        this.event.setCancelled(b);
    }
}
