package ru.ancap.framework.plugin.api.events.wrapper;

import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Calling, when some player tries to heal villager
 */

public class AncapVillagerHealEvent extends AncapEvent implements Cancellable {

    public static final HandlerList handlers = new HandlerList();

    private final @NotNull Cancellable event;
    private final @NotNull Player player;
    private final @NotNull Villager villager;

    public AncapVillagerHealEvent(@NotNull Cancellable event, @NotNull Player player, @NotNull Villager villager) {
        this.event = event;
        this.player = player;
        this.villager = villager;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public boolean isCancelled() {
        return this.event.isCancelled();
    }

    @Override
    public void setCancelled(boolean b) {
        this.event.setCancelled(b);
    }

    @NotNull
    public Cancellable getBukkitEvent() {
        return this.event;
    }

    @NotNull
    public Player getPlayer() {
        return this.player;
    }

    @NotNull
    public Villager getVillager() {
        return this.villager;
    }

}
