package ru.ancap.framework.plugin.api.packetapi.packet.modules.sound.builder;


import ru.ancap.framework.plugin.api.packetapi.packet.modules.sound.Sound;

public interface SoundBuilder {

    void setSound(String name);
    void setVolume(float volume);
    void setPitch(float pitch);

    Sound build();
}
