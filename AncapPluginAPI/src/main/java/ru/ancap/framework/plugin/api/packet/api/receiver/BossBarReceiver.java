package ru.ancap.framework.plugin.api.packet.api.receiver;

public interface BossBarReceiver {

    void sendBossBar(String text, float progress, String colorData, String notchesData, int duration);
}
