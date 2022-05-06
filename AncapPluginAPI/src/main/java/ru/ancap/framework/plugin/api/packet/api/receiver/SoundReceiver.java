package ru.ancap.framework.plugin.api.packet.api.receiver;

public interface SoundReceiver {

    void sendSound(String soundName, float volume, float pitch);
}
