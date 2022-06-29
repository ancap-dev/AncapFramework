package ru.ancap.framework.api.event.events.wrapper;

import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

/**
 * Calling, when some player tries to heal villager
 */

public class VillagerHealEvent extends AncapWrapperPlayerEvent implements Cancellable {

    public static final HandlerList handlers = new HandlerList();

    private final @NotNull Villager villager;

    public VillagerHealEvent(@NotNull Cancellable event, @NotNull Player player, @NotNull Villager villager) {
        super(event, player);
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

    @NotNull
    public Villager getVillager() {
        return this.villager;
    }

}
