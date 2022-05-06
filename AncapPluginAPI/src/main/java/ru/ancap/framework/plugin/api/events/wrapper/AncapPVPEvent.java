package ru.ancap.framework.plugin.api.events.wrapper;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Calling, when some player attacks other player(s).
 */

public class AncapPVPEvent extends AncapEvent implements Cancellable {

    public static final HandlerList handlers = new HandlerList();

    private final @NotNull Player[] players;
    private final @NotNull Cancellable event;

    public AncapPVPEvent(@NotNull Cancellable event, @NotNull Player... players) {
        this.players = players;
        this.event = event;
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
    public Player[] getPlayers() {
        return this.players;
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
