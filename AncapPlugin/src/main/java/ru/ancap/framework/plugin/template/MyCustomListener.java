package ru.ancap.framework.plugin.template;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerBucketEntityEvent;

public class MyCustomListener extends AncapEventListener {

    @EventHandler
    public void on(PlayerBucketEntityEvent event) {
        throw new RuntimeException();
    }
}
