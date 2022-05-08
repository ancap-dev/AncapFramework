package ru.ancap.framework.plugin.api.configuration.extended;

import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import ru.ancap.framework.plugin.api.configuration.AncapWrappedConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.api.BalanceConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.api.MapConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.api.SendableConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.exceptions.InvalidConfigurationBalanceException;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.exceptions.InvalidConfigurationMapException;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.exceptions.InvalidConfigurationSendableException;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.impl.AncapBalanceConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.impl.AncapMapConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.impl.AncapPacketConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.placeholder.AncapPlaceholderedConfigurationSection;
import ru.ancap.framework.plugin.api.packet.api.packet.Sendable;
import ru.ancap.framework.plugin.api.packet.api.packet.builder.PacketBuilderSource;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.actionbar.builder.ActionBarBuilderSource;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.bossbar.builder.BossBarBuilderSource;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.message.builder.MessageBuilderSource;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.sound.builder.SoundBuilderSource;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.title.builder.TitleBuilderSource;
import ru.ancap.misc.economy.balance.Balance;
import ru.ancap.misc.economy.balance.factory.BalanceFactory;
import ru.ancap.misc.placeholder.PlaceholderSource;

import java.util.Map;

public class AncapExtendedConfigurationSection extends AncapPlaceholderedConfigurationSection implements ExtendedConfigurationSection {

    private BalanceFactory balanceFactory;
    private PacketBuilderSource packetBuilderSource;
    private MessageBuilderSource messageBuilderSource;
    private ActionBarBuilderSource actionBarBuilderSource;
    private SoundBuilderSource soundBuilderSource;
    private BossBarBuilderSource bossBarBuilderSource;
    private TitleBuilderSource titleBuilderSource;

    public AncapExtendedConfigurationSection(ConfigurationSection configurationSection,
                                             PlaceholderSource source,
                                             BalanceFactory factory,
                                             PacketBuilderSource packetBuilderSource,
                                             MessageBuilderSource messageBuilderSource,
                                             ActionBarBuilderSource actionBarBuilderSource,
                                             SoundBuilderSource soundBuilderSource,
                                             BossBarBuilderSource bossBarBuilderSource,
                                             TitleBuilderSource titleBuilderSource) {
        super(configurationSection, source);
        this.balanceFactory = factory;
        this.packetBuilderSource = packetBuilderSource;
        this.messageBuilderSource = messageBuilderSource;
        this.actionBarBuilderSource = actionBarBuilderSource;
        this.soundBuilderSource = soundBuilderSource;
        this.bossBarBuilderSource = bossBarBuilderSource;
        this.titleBuilderSource = titleBuilderSource;
    }

    public AncapExtendedConfigurationSection(ConfigurationSection configurationSection,
                                             BalanceFactory factory,
                                             PacketBuilderSource packetBuilderSource,
                                             MessageBuilderSource messageBuilderSource,
                                             ActionBarBuilderSource actionBarBuilderSource,
                                             SoundBuilderSource soundBuilderSource,
                                             BossBarBuilderSource bossBarBuilderSource,
                                             TitleBuilderSource titleBuilderSource) {
        super(configurationSection);
        this.balanceFactory = factory;
        this.packetBuilderSource = packetBuilderSource;
        this.messageBuilderSource = messageBuilderSource;
        this.actionBarBuilderSource = actionBarBuilderSource;
        this.soundBuilderSource = soundBuilderSource;
        this.bossBarBuilderSource = bossBarBuilderSource;
        this.titleBuilderSource = titleBuilderSource;
    }

    public AncapExtendedConfigurationSection(AncapExtendedConfigurationSection section) {
        this(section,
                section.getBalanceFactory(),
                section.getPacketBuilderSource(),
                section.getMessageBuilderSource(),
                section.getActionBarBuilderSource(),
                section.getSoundBuilderSource(),
                section.getBossBarBuilderSource(),
                section.getTitleBuilderSource()
        );
    }

    protected BalanceFactory getBalanceFactory() {
        return this.balanceFactory;
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
    public Balance getBalance() throws InvalidConfigurationBalanceException {
        BalanceConfigurationSection section = new AncapBalanceConfigurationSection(
                new AncapWrappedConfigurationSection(this),
                this.getBalanceFactory()
        );
        return section.getBalance();
    }

    @Override
    public Balance getBalance(String path) throws InvalidConfigurationBalanceException {
        BalanceConfigurationSection section = new AncapBalanceConfigurationSection(
                new AncapWrappedConfigurationSection(
                        this.getConfigurationSection(path)
                ),
                this.getBalanceFactory()
        );
        return section.getBalance();
    }

    @Override
    public Sendable getPacket() throws InvalidConfigurationSendableException {
        SendableConfigurationSection section = new AncapPacketConfigurationSection(
                new AncapWrappedConfigurationSection(this),
                this.getPacketBuilderSource(),
                this.getMessageBuilderSource(),
                this.getActionBarBuilderSource(),
                this.getSoundBuilderSource(),
                this.getBossBarBuilderSource(),
                this.getTitleBuilderSource()
        );
        return section.getSendable();
    }

    @Override
    public Sendable getPacket(String path) throws InvalidConfigurationSendableException {
        SendableConfigurationSection section = new AncapPacketConfigurationSection(
                new AncapWrappedConfigurationSection(
                        this.getConfigurationSection(path)
                ),
                this.getPacketBuilderSource(),
                this.getMessageBuilderSource(),
                this.getActionBarBuilderSource(),
                this.getSoundBuilderSource(),
                this.getBossBarBuilderSource(),
                this.getTitleBuilderSource()
        );
        return section.getSendable();
    }

    @Override
    public Map<String, String> getMap() throws InvalidConfigurationMapException {
        MapConfigurationSection section = new AncapMapConfigurationSection(this);
        return section.getMap();
    }

    @Override
    public Map<String, String> getMap(String path) throws InvalidConfigurationMapException {
        MapConfigurationSection section = new AncapMapConfigurationSection(this);
        return section.getMap();
    }

    @Override
    public @NotNull ExtendedConfigurationSection getConfigurationSection(@NotNull String path) {
        return new AncapExtendedConfigurationSection(
                super.getConfigurationSection(path),
                this.getBalanceFactory(),
                this.getPacketBuilderSource(),
                this.getMessageBuilderSource(),
                this.getActionBarBuilderSource(),
                this.getSoundBuilderSource(),
                this.getBossBarBuilderSource(),
                this.getTitleBuilderSource()
        );
    }
}
