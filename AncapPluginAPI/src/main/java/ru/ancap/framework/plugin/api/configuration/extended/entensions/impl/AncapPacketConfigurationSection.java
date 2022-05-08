package ru.ancap.framework.plugin.api.configuration.extended.entensions.impl;

import org.bukkit.configuration.ConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.AncapWrappedConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.api.SendableConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.exceptions.InvalidConfigurationSendableException;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.impl.factory.AncapSendableConfigurationSectionFactory;
import ru.ancap.framework.plugin.api.packet.api.packet.Packet;
import ru.ancap.framework.plugin.api.packet.api.packet.Sendable;
import ru.ancap.framework.plugin.api.packet.api.packet.builder.PacketBuilder;
import ru.ancap.framework.plugin.api.packet.api.packet.builder.PacketBuilderSource;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.actionbar.builder.ActionBarBuilderSource;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.bossbar.builder.BossBarBuilderSource;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.message.builder.MessageBuilderSource;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.sound.builder.SoundBuilderSource;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.title.builder.TitleBuilderSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AncapPacketConfigurationSection extends AncapWrappedConfigurationSection implements SendableConfigurationSection {

    private PacketBuilderSource packetBuilderSource;
    private MessageBuilderSource messageBuilderSource;
    private ActionBarBuilderSource actionBarBuilderSource;
    private SoundBuilderSource soundBuilderSource;
    private BossBarBuilderSource bossBarBuilderSource;
    private TitleBuilderSource titleBuilderSource;

    public AncapPacketConfigurationSection(AncapWrappedConfigurationSection section,
                                           PacketBuilderSource packetBuilderSource,
                                           MessageBuilderSource messageBuilderSource,
                                           ActionBarBuilderSource actionBarBuilderSource,
                                           SoundBuilderSource soundBuilderSource,
                                           BossBarBuilderSource bossBarBuilderSource,
                                           TitleBuilderSource titleBuilderSource) {
        super(section);
        this.packetBuilderSource = packetBuilderSource;
        this.messageBuilderSource = messageBuilderSource;
        this.actionBarBuilderSource = actionBarBuilderSource;
        this.soundBuilderSource = soundBuilderSource;
        this.bossBarBuilderSource = bossBarBuilderSource;
        this.titleBuilderSource = titleBuilderSource;
    }

    public AncapPacketConfigurationSection(AncapPacketConfigurationSection section) {
        this(section,
                section.getPacketBuilderSource(),
                section.getMessageBuilderSource(),
                section.getActionBarBuilderSource(),
                section.getSoundBuilderSource(),
                section.getBossBarBuilderSource(),
                section.getTitleBuilderSource()
        );
    }

    protected PacketBuilderSource getPacketBuilderSource() {
        return this.packetBuilderSource;
    }

    protected MessageBuilderSource getMessageBuilderSource() {
        return this.messageBuilderSource;
    }

    protected ActionBarBuilderSource getActionBarBuilderSource() {
        return this.actionBarBuilderSource;
    }

    protected SoundBuilderSource getSoundBuilderSource() {
        return this.soundBuilderSource;
    }

    protected BossBarBuilderSource getBossBarBuilderSource() {
        return this.bossBarBuilderSource;
    }

    protected TitleBuilderSource getTitleBuilderSource() {
        return this.titleBuilderSource;
    }

    @Override
    public Packet getSendable() throws InvalidConfigurationSendableException {
        ConfigurationSection operatedSection = this.getSection();
        Set<String> keys = operatedSection.getKeys(false);
        this.doChecks(keys);
        List<Sendable> sendables = this.getDirectSendables(operatedSection);
        List<SendableConfigurationSection> subSectionList = this.getSubSections(operatedSection, keys);
        PacketBuilder builder = this.packetBuilderSource.getPacketBuilder();
        for (SendableConfigurationSection subSection : subSectionList) {
            builder.addSendable(subSection.getSendable());
        }
        for (Sendable sendable : sendables) {
            builder.addSendable(sendable);
        }
        return builder.build();
    }

    // fucking bukkit configuration sections forced me to do this shit, and i REALLY dont want to think how to do it better
    private List<Sendable> getDirectSendables(ConfigurationSection section) {
        List<Sendable> sendables = new ArrayList<>();
        String path = "message";
        if (section.isSet(path)) {
            sendables.add(this.getMessageBuilderSource().getMessageBuilder().setTexts(section.getStringList(path)).build());
        }
        path = "messages";
        if (section.isSet(path)) {
            sendables.add(this.getMessageBuilderSource().getMessageBuilder().setTexts(section.getStringList(path)).build());
        }
        path = "action-bar";
        if (section.isSet(path)) {
            sendables.add(this.getActionBarBuilderSource().getActionBarBuilder().setText(section.getString(path)).build());
        }
        path = "actionbar";
        if (section.isSet(path)) {
            sendables.add(this.getActionBarBuilderSource().getActionBarBuilder().setText(section.getString(path)).build());
        }
        return sendables;
    }

    private List<SendableConfigurationSection> getSubSections(ConfigurationSection section, Set<String> keys) throws InvalidConfigurationSendableException {
        List<ConfigurationSection> sections = new ArrayList<>();
        for (String key : keys) {
            sections.add(section.getConfigurationSection(key));
        }
        AncapSendableConfigurationSectionFactory factory = this.createSendableConfigurationFactory();
        List<SendableConfigurationSection> sendableConfigurationSections = new ArrayList<>();
        for (ConfigurationSection loadedSection : sections) {
            sendableConfigurationSections.add(factory.getFrom(loadedSection));
        }
        return sendableConfigurationSections;
    }

    private AncapSendableConfigurationSectionFactory createSendableConfigurationFactory() {
        return new AncapSendableConfigurationSectionFactory(
                this.getPacketBuilderSource(),
                this.getMessageBuilderSource(),
                this.getActionBarBuilderSource(),
                this.getSoundBuilderSource(),
                this.getBossBarBuilderSource(),
                this.getTitleBuilderSource()
        );
    }

    /**
     * Method, that checks for possible bad config filling and throws runtime exception, if config is filled badly.
     */

    private void doChecks(Set<String> keys) throws InvalidConfigurationSendableException {
        this.validateKeys(keys);
    }

    private void validateKeys(Set<String> keys) throws InvalidConfigurationSendableException {
        this.validateKeysCount(keys);
    }

    private void validateKeysCount(Set<String> keys) throws InvalidConfigurationSendableException {
        if (keys.isEmpty()) {
            throw new InvalidConfigurationSendableException(this.getSection().getCurrentPath(), "Packet section cannot be empty!");
        }
    }
}
