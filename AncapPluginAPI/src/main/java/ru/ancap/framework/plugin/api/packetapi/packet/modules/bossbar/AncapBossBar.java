package ru.ancap.framework.plugin.api.packetapi.packet.modules.bossbar;

import ru.ancap.framework.plugin.api.packetapi.receiver.PacketReceiver;

/**
 * Highly recommended not to use this class directly. Use PacketBuilders!
 */

public class AncapBossBar implements BossBar {

    private String name;
    private float progress;
    private String colorData;
    private String notchesData;
    private int duration;

    public AncapBossBar(String name, float progress, String colorData, String notchesData, int duration) {
        this.name = name;
        this.progress = progress;
        this.colorData = colorData;
        this.notchesData = notchesData;
        this.duration = duration;
    }

    @Override
    public void sendTo(PacketReceiver receiver) {
        receiver.sendBossBar(this.name, this.progress, this.colorData, this.notchesData, duration);
    }
}
