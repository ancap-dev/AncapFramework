package ru.ancap.framework.plugin.plugin.events.timer.heartbeat;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import ru.ancap.framework.plugin.api.events.time.heartbeat.AncapHeartbeatEvent;

public class AncapHeartbeatRunnable extends BukkitRunnable {

    @Override
    public void run() {
        AncapHeartbeatEvent event = new AncapHeartbeatEvent();
        Bukkit.getPluginManager().callEvent(event);
    }
}
