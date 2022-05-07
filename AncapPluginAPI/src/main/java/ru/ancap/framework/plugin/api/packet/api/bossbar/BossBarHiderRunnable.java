package ru.ancap.framework.plugin.api.packet.api.bossbar;

import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.entity.Player;

public class BossBarHiderRunnable extends Thread {

    private Player player;
    private BossBar bossBar;
    private int duration;

    public BossBarHiderRunnable(Player player, BossBar bossBar, int duration) {
        this.player = player;
        this.bossBar = bossBar;
        this.duration = duration;
    }

    protected Player getPlayer() {
        return this.player;
    }

    protected BossBar getBossBar() {
        return this.bossBar;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(duration* 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
        this.player.hideBossBar(bossBar);
    }
}
