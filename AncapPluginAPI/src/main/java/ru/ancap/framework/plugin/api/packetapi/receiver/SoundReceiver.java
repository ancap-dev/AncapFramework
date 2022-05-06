package ru.ancap.framework.plugin.api.packetapi.receiver;

public interface SoundReceiver {

    void sendSound(String soundName, float volume, float pitch);
}
