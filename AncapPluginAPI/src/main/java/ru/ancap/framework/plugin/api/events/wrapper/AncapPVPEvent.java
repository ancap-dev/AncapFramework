package ru.ancap.framework.plugin.api.events.wrapper;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Calling, when some player attacks other player(s).
 */

public class AncapPVPEvent extends AncapWrapperCancellableEvent implements Cancellable {

    public static final HandlerList handlers = new HandlerList();

    private final @NotNull Player[] players;

    public AncapPVPEvent(@NotNull Cancellable event, @NotNull Player... players) {
        super(event);
        this.players = players;
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
}
