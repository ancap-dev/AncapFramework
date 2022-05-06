package ru.ancap.framework.plugin.api.packet.api.packet.receiver;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import ru.ancap.framework.plugin.api.packet.api.packet.Sendable;
import ru.ancap.framework.plugin.api.packet.api.receiver.PacketReceiver;

/**
 * Mainly technical class. Use PacketAPI and builders to send and build packets for players.
 * For custom data holding use MetaholderPlayer. For direct interaction with players on server inherit this
 * class and add ur own methods. Don't use bukkit player methods! It ruins encapsulation.
 */

public class AncapBukkitPacketReceiver implements PacketReceiver {

    private Player bukkitPlayer;
    private BossBar bossBar;

    public AncapBukkitPacketReceiver(Player player) {
        this.bukkitPlayer = player;
    }

    protected Player getBukkitPlayer() {
        return this.bukkitPlayer;
    }

    protected BossBar getBossBar() {
        return this.bossBar;
    }

    @Override
    public void sendSound(String soundName, float volume, float pitch) {
        this.bukkitPlayer.playSound(bukkitPlayer.getLocation(), Sound.valueOf(soundName.toUpperCase()), volume, pitch);
    }

    @Override
    public void sendString(String string) {
        this.bukkitPlayer.sendMessage(string);
    }

    @Override
    public void sendTitle(String title, String subtitle, int fadeIn, int time, int fadeOut) {
        this.bukkitPlayer.sendTitle(title, subtitle, fadeIn, time, fadeOut);
    }

    @Override
    public void sendBossBar(String text, float progress, String colorData, String notchesData, int duration) {
        this.hideBossbar();
        this.sendNewBossBar(text, progress, colorData, notchesData, duration);
    }

    private void sendNewBossBar(String text, float progress, String colorData, String notchesData, int duration) {
        BossBar bar = this.getBossBarFor(text, progress, colorData, notchesData);
        this.registerBossBar(bar);
        this.sendAutoHidingBossbar(bar, duration);
    }

    private void sendAutoHidingBossbar(BossBar bar, int livingTime) {
        this.bukkitPlayer.showBossBar(bar);
        this.hideBossBarLater(bar, livingTime);
    }

    private void hideBossBarLater(BossBar bar, int livingTime) {
        BossBarHider hider = new BossBarHider(bukkitPlayer, bar, livingTime);
        hider.start();
    }

    private void registerBossBar(BossBar bar) {
        this.bossBar = bar;
    }

    private BossBar getBossBarFor(String text, float progress, String colorData, String notchesData) {
        TextComponent component = Component.text(text);
        BossBar.Color color = BossBar.Color.valueOf(colorData.toUpperCase());
        BossBar.Overlay overlay = BossBar.Overlay.valueOf(notchesData.toUpperCase());
        return BossBar.bossBar(component, progress, color, overlay);
    }

    private void hideBossbar() {
        if (this.bossBar != null) {
            bukkitPlayer.hideBossBar(this.bossBar);
            this.bossBar = null;
        }
    }

    @Override
    public void sendActionBar(String actionBar) {
        this.bukkitPlayer.sendActionBar(actionBar);
    }

    @Override
    public void send(Sendable sendable) {
        sendable.sendTo(this);
    }
}
