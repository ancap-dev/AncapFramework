package ru.ancap.framework.plugin.api.packetapi.receiver;

public interface TitleReceiver {

    void sendTitle(String title, String subtitle, int fadeIn, int time, int fadeOut);
}
