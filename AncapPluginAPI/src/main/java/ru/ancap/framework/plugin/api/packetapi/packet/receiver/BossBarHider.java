package ru.ancap.framework.plugin.api.packetapi.packet.receiver;

import net.kyori.adventure.bossbar.BossBar;
import org.bukkit.entity.Player;

class BossBarHider extends Thread {

    private Player bukkitPlayer;
    private BossBar bossBar;
    private int livingTime;

    public BossBarHider(Player player, BossBar bossBar, int livingTime) {
        this.bukkitPlayer = player;
        this.bossBar = bossBar;
        this.livingTime = livingTime;
    }

    public BossBarHider(BossBarHider hider) {
        this(hider.getBukkitPlayer(), hider.getBossBar(), hider.getLivingTime());
    }

    protected Player getBukkitPlayer() {
        return this.bukkitPlayer;
    }

    protected BossBar getBossBar() {
        return this.bossBar;
    }

    protected int getLivingTime() {
        return this.livingTime;
    }

    @Override
    public void run() {
        this.waitForHide();
        this.hide();
    }

    private void hide() {
        this.bukkitPlayer.hideBossBar(this.bossBar);
    }

    private void waitForHide() {
        try {
            Thread.sleep(this.getLivingTime()*1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}