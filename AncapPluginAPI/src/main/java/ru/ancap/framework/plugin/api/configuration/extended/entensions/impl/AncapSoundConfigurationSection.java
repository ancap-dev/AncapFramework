package ru.ancap.framework.plugin.api.configuration.extended.entensions.impl;

import org.bukkit.configuration.ConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.AncapWrappedConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.api.SendableConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.exceptions.InvalidConfigurationSendableException;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.sound.Sound;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.sound.builder.SoundBuilder;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.sound.builder.SoundBuilderSource;

public class AncapSoundConfigurationSection extends AncapWrappedConfigurationSection implements SendableConfigurationSection {

    private SoundBuilderSource builderSource;

    public AncapSoundConfigurationSection(AncapWrappedConfigurationSection section, SoundBuilderSource soundBuilderSource) {
        super(section);
        this.builderSource = soundBuilderSource;
    }

    public AncapSoundConfigurationSection(AncapSoundConfigurationSection section) {
        this(section, section.getSoundBuilderSource());
    }

    protected SoundBuilderSource getSoundBuilderSource() {
        return this.builderSource;
    }

    @Override
    public Sound getSendable() throws InvalidConfigurationSendableException {
        SoundBuilder soundBuilder = this.builderSource.getSoundBuilder();
        ConfigurationSection section = this.getSection();
        String name = "name";
        if (section.isSet(name)) {
            String soundName = section.getString(name);
            try {
                org.bukkit.Sound.valueOf(soundName.toUpperCase());
            } catch (RuntimeException e) {
                throw new InvalidConfigurationSendableException(section.getCurrentPath(), soundName+" is not a sound");
            }
            soundBuilder.setSound(soundName);
        }
        String volume = "volume";
        if (this.getSection().isSet(volume)) {
            String volumeString = section.getString(volume);
            try {
                soundBuilder.setVolume(Float.parseFloat(volumeString));
            } catch (NumberFormatException e) {
                throw new InvalidConfigurationSendableException(section.getCurrentPath(), volumeString+" is not a volume");
            }
        }
        String pitch = "pitch";
        if (this.getSection().isSet(pitch)) {
            String pitchString = section.getString(pitch);
            try {
                soundBuilder.setPitch(Float.parseFloat(pitchString));
            } catch (NumberFormatException e) {
                throw new InvalidConfigurationSendableException(section.getCurrentPath(), pitchString+" is not a pitch");
            }
        }
        return soundBuilder.build();
    }
}
