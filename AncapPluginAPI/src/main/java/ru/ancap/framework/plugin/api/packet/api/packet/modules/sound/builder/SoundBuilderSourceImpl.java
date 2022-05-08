package ru.ancap.framework.plugin.api.packet.api.packet.modules.sound.builder;

public class SoundBuilderSourceImpl implements SoundBuilderSource {

    @Override
    public SoundBuilder getSoundBuilder() {
        return new SoundBuilderImpl();
    }
}
