package ru.ancap.framework.plugin.api.configuration.extended.entensions.impl.factory;

import org.bukkit.configuration.ConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.AncapWrappedConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.api.SendableConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.exceptions.InvalidConfigurationSendableException;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.impl.*;
import ru.ancap.framework.plugin.api.packet.api.packet.builder.PacketBuilderSource;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.actionbar.builder.ActionBarBuilderSource;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.bossbar.builder.BossBarBuilderSource;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.message.builder.MessageBuilderSource;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.sound.builder.SoundBuilderSource;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.title.builder.TitleBuilderSource;

public class AncapSendableConfigurationSectionFactory implements SendableConfigurationSectionFactory {

    private PacketBuilderSource packetBuilderSource;
    private MessageBuilderSource messageBuilderSource;
    private ActionBarBuilderSource actionBarBuilderSource;
    private SoundBuilderSource soundBuilderSource;
    private BossBarBuilderSource bossBarBuilderSource;
    private TitleBuilderSource titleBuilderSource;

    public AncapSendableConfigurationSectionFactory(PacketBuilderSource packetBuilderSource,
                                                    MessageBuilderSource messageBuilderSource,
                                                    ActionBarBuilderSource actionBarBuilderSource,
                                                    SoundBuilderSource soundBuilderSource,
                                                    BossBarBuilderSource bossBarBuilderSource,
                                                    TitleBuilderSource titleBuilderSource) {
        this.packetBuilderSource = packetBuilderSource;
        this.messageBuilderSource = messageBuilderSource;
        this.actionBarBuilderSource = actionBarBuilderSource;
        this.soundBuilderSource = soundBuilderSource;
        this.bossBarBuilderSource = bossBarBuilderSource;
        this.titleBuilderSource = titleBuilderSource;
    }

    public AncapSendableConfigurationSectionFactory(AncapSendableConfigurationSectionFactory factory) {
        this(factory.getPacketBuilderSource(),
                factory.getMessageBuilderSource(),
                factory.getActionBarBuilderSource(),
                factory.getSoundBuilderSource(),
                factory.getBossBarBuilderSource(),
                factory.getTitleBuilderSource()
        );
    }

    protected PacketBuilderSource getPacketBuilderSource() {
        return packetBuilderSource;
    }

    protected MessageBuilderSource getMessageBuilderSource() {
        return messageBuilderSource;
    }

    protected ActionBarBuilderSource getActionBarBuilderSource() {
        return actionBarBuilderSource;
    }

    protected SoundBuilderSource getSoundBuilderSource() {
        return soundBuilderSource;
    }

    protected BossBarBuilderSource getBossBarBuilderSource() {
        return bossBarBuilderSource;
    }

    protected TitleBuilderSource getTitleBuilderSource() {
        return this.titleBuilderSource;
    }

    @Override
    public SendableConfigurationSection getFrom(ConfigurationSection section) throws InvalidConfigurationSendableException {
        String name = section.getName();
        if (name.equalsIgnoreCase("sound")) {
            return new AncapSoundConfigurationSection(
                    new AncapWrappedConfigurationSection(section),
                    this.getSoundBuilderSource()
            );
        }
        if (name.equalsIgnoreCase("title")) {
            return new AncapTitleConfigurationSection(
                    new AncapWrappedConfigurationSection(section),
                    this.getTitleBuilderSource()
            );
        }
        if (name.equalsIgnoreCase("bossbar") || name.equalsIgnoreCase("boss-bar") ) {
            return new AncapBossBarConfigurationSection(
                    new AncapWrappedConfigurationSection(section),
                    this.getBossBarBuilderSource()
            );
        }
        throw new InvalidConfigurationSendableException(section.getCurrentPath(), "Invalid sendable name: "+name);
    }
}
