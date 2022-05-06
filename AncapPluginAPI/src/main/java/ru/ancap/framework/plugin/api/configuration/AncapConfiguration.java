package ru.ancap.framework.plugin.api.configuration;

import org.jetbrains.annotations.NotNull;
import ru.ancap.framework.plugin.api.configuration.balance.AncapBalanceConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.balance.BalanceConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.bukkitwrapper.AncapBukkitConfiguration;
import ru.ancap.framework.plugin.api.configuration.exception.BadConfigurationException;
import ru.ancap.framework.plugin.api.configuration.exception.InvalidConfigurationBalanceException;
import ru.ancap.framework.plugin.api.configuration.exception.InvalidConfigurationPacketException;
import ru.ancap.framework.plugin.api.configuration.packet.AncapPacketConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.packet.PacketConfigurationSection;
import ru.ancap.framework.plugin.api.packet.api.packet.Packet;
import ru.ancap.framework.plugin.api.packet.api.packet.builder.AncapPacketBuilderSource;
import ru.ancap.misc.economy.balance.Balance;
import ru.ancap.misc.economy.balance.factory.BalanceFactory;
import ru.ancap.misc.placeholder.Placeholder;
import ru.ancap.misc.placeholder.PlaceholderSource;
import ru.ancap.misc.placeholder.PlaceholderSourceBuilder;
import ru.ancap.misc.placeholder.PlaceholderSourceBuilderSource;
import ru.ancap.misc.placeholder.exception.NoSuchPlaceholderException;

import java.util.List;

public abstract class AncapConfiguration extends AncapBukkitConfiguration implements PlaceholderedConfiguration, PlaceholderSource {

    private PlaceholderSourceBuilderSource placeholderBuilderSource;
    private AncapPacketBuilderSource packetBuilderSource;
    private BalanceFactory factory;

    public AncapConfiguration(org.bukkit.configuration.Configuration configuration,
                              AncapPacketBuilderSource packetBuilderSource,
                              BalanceFactory factory,
                              PlaceholderSourceBuilderSource placeholderBuilderSource) {
        super(configuration);
        this.packetBuilderSource = packetBuilderSource;
        this.factory = factory;
        this.placeholderBuilderSource = placeholderBuilderSource;
    }

    public AncapConfiguration(AncapConfiguration configuration) {
        this(configuration.getConfiguration(),
                configuration.getPacketBuilderSource(),
                configuration.getBalanceFactory(),
                configuration.getPlaceholderSourceBuilderSource());
    }

    protected AncapPacketBuilderSource getPacketBuilderSource() {
        return this.packetBuilderSource;
    }

    protected BalanceFactory getBalanceFactory() {
        return this.factory;
    }

    protected PlaceholderSourceBuilderSource getPlaceholderSourceBuilderSource() {
        return this.placeholderBuilderSource;
    }

    public PlaceholderSourceBuilder getNewSourceBuilder() {
        PlaceholderSourceBuilder builder = this.getPlaceholderSourceBuilderSource().getPlaceholderSourceBuilder();
        builder.addSource(this);
        return builder;
    }

    @Override
    public Packet getPacket(String path) {
        return this.getPacket(path, this);
    }

    public Packet getPacket(String path, PlaceholderSource source) {
        PacketConfigurationSection section = new AncapPacketConfigurationSection(
                this.getPlaceholderedConfigurationSection(path, source),
                this.getPacketBuilderSource()
        );
        try {
            return section.getPacket();
        } catch (InvalidConfigurationPacketException e) {
            throw new BadConfigurationException(e.getPath(), e.getReason());
        }
    }

    @Override
    public Balance getBalance(String path) {
        return this.getBalance(path, this);
    }

    @Override
    public Balance getBalance(String path, PlaceholderSource source) {
        BalanceConfigurationSection section = new AncapBalanceConfigurationSection(
                this.getPlaceholderedConfigurationSection(path, source),
                this.getBalanceFactory()
        );
        try {
            return section.getBalance();
        } catch (InvalidConfigurationBalanceException e) {
            throw new BadConfigurationException(e.getPath(), e.getReason());
        }
    }

    @Override
    public AncapPlaceholderedConfigurationSection getConfigurationSection(@NotNull String path) {
        return this.getPlaceholderedConfigurationSection(path, this);
    }

    @Override
    public @NotNull List<String> getStringList(@NotNull String path) {
        return this.getStringList(path, this);
    }

    public List<String> getStringList(String path, PlaceholderSource source) {
        return this.getPlaceholderedConfigurationSection(source).getStringList(path);
    }

    @Override
    public @NotNull String getString(@NotNull String path) {
        return this.getString(path, this);
    }

    public String getString(String path, PlaceholderSource source) {
        return this.getPlaceholderedConfigurationSection(source).getString(path);
    }

    private AncapPlaceholderedConfigurationSection getPlaceholderedConfigurationSection(PlaceholderSource source) {
        return new AncapPlaceholderedConfigurationSection(super.getConfigurationSection(), source);
    }

    private AncapPlaceholderedConfigurationSection getPlaceholderedConfigurationSection(@NotNull String path, PlaceholderSource source) {
        return new AncapPlaceholderedConfigurationSection(super.getConfigurationSection(path), source);
    }

    @Override
    public String getValue(Placeholder placeholder) throws NoSuchPlaceholderException {
        if (!this.isSet(placeholder.getName())) {
            throw new NoSuchPlaceholderException(placeholder);
        }
        return this.getString(placeholder.getName());
    }

}
