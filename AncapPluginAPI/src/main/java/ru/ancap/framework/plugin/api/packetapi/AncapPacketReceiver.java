package ru.ancap.framework.plugin.api.packetapi;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import ru.ancap.framework.plugin.api.packetapi.bossbar.BossBarHiderRunnable;
import ru.ancap.framework.plugin.api.packetapi.bossbar.BossBarPool;
import ru.ancap.framework.plugin.api.packetapi.bossbar.NoSuchBossBarException;
import ru.ancap.framework.plugin.api.packetapi.packet.Sendable;
import ru.ancap.framework.plugin.api.packetapi.receiver.PacketReceiver;

public class AncapPacketReceiver implements PacketReceiver {

    private Player player;

    public AncapPacketReceiver(Player player) {
        this.player = player;
    }

    protected Player getPlayer() {
        return this.player;
    }

    @Override
    public void sendActionBar(String actionBar) {
        this.player.sendActionBar(actionBar);
    }

    @Override
    public void sendBossBar(String text, float progress, String colorData, String notchesData, int duration) {
        this.hidePreviousBossBar();
        final Component name = Component.text(text);
        final BossBar bossBar = BossBar.bossBar(
                name,
                progress,
                BossBar.Color.valueOf(colorData.toUpperCase()),
                BossBar.Overlay.valueOf(notchesData.toUpperCase()));
        String playerName = this.getPlayer().getName();
        BossBarPool.poll(playerName, bossBar);
        this.player.showBossBar(bossBar);
        new BossBarHiderRunnable(player, bossBar, duration).start();
    }

    private void hidePreviousBossBar() {
        BossBar bossBar;
        try {
            bossBar = BossBarPool.find(this.getPlayer().getName());
        } catch (NoSuchBossBarException e) {
            return;
        }
        this.getPlayer().hideBossBar(bossBar);
    }

    @Override
    public void send(Sendable sendable) {
        sendable.sendTo(this);
    }

    @Override
    public void sendSound(String soundName, float volume, float pitch) {
        this.player.playSound(this.player.getLocation(), soundName, volume, pitch);
    }

    @Override
    public void sendString(String string) {
        this.player.sendMessage(string);
    }

    @Override
    public void sendTitle(String title, String subtitle, int fadeIn, int time, int fadeOut) {
        this.player.sendTitle(title, subtitle, fadeIn, time, fadeOut);
    }
}
