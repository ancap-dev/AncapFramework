package ru.ancap.framework.plugin.plugin.events.listeners.wrapper;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.plugin.PluginManager;
import ru.ancap.framework.plugin.api.events.wrapper.AncapVillagerHealEvent;
import ru.ancap.framework.plugin.plugin.events.listeners.AncapListener;

public class VillagerHealListener extends AncapListener {

    public VillagerHealListener(PluginManager pluginManager) {
        super(pluginManager);
    }

    public VillagerHealListener() {
        super();
    }

    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent e) {
        if (this.isHealingVillagerAt(e)) {
            this.throwEvent(e, e.getPlayer(), (Villager) e.getRightClicked());
        }
    }

    private boolean isHealingVillagerAt(PlayerInteractEntityEvent e) {
        if (e.getRightClicked().getType() == EntityType.ZOMBIE_VILLAGER && e.getPlayer().getItemInHand().getType() == Material.GOLDEN_APPLE) {
            return true;
        }
        return false;
    }

    private void throwEvent(PlayerInteractEntityEvent e, Player player, Villager rightClicked) {
        this.throwEvent(new AncapVillagerHealEvent(e, player, rightClicked));
    }
}
