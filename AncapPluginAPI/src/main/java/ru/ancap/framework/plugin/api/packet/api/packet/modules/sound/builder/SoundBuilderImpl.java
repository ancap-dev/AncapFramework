package ru.ancap.framework.plugin.api.packet.api.packet.modules.sound.builder;

import ru.ancap.framework.plugin.api.packet.api.packet.modules.sound.AncapSound;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.sound.Sound;

public class SoundBuilderImpl implements SoundBuilder {

    private String name = "";
    private float volume = 1f;
    private float pitch = 1f;

    @Override
    public void setSound(String name) {
        this.name = name;
    }

    @Override
    public void setVolume(float volume) {
        this.volume = volume;
    }

    @Override
    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    @Override
    public Sound build() {
        return new AncapSound(name, volume, pitch);
    }
}
