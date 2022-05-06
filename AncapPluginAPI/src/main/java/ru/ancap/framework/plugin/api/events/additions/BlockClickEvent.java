package ru.ancap.framework.plugin.api.events.additions;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class BlockClickEvent extends Event implements Cancellable {

    public static final HandlerList handlers = new HandlerList();

    private @NotNull Cancellable event;
    private @NotNull Block block;
    private @NotNull Player clicker;

    public BlockClickEvent(@NotNull Cancellable event, @NotNull Block block, @NotNull Player player) {
        this.event = event;
        this.block = block;
        this.clicker = player;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public Cancellable getBukkitEvent() {
        return this.event;
    }

    @NotNull
    public Block getBlock() {
        return this.block;
    }

    @NotNull
    public Player getClicker() {
        return this.clicker;
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
