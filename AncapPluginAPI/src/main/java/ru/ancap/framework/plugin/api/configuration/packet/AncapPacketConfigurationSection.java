package ru.ancap.framework.plugin.api.configuration.packet;

import ru.ancap.framework.plugin.api.configuration.AncapPlaceholderedConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.exception.InvalidConfigurationPacketException;
import ru.ancap.framework.plugin.api.packet.api.packet.Packet;
import ru.ancap.framework.plugin.api.packet.api.packet.builder.AncapPacketBuilder;
import ru.ancap.framework.plugin.api.packet.api.packet.builder.AncapPacketBuilderSource;

import java.util.NoSuchElementException;

public class AncapPacketConfigurationSection extends AncapPlaceholderedConfigurationSection implements PacketConfigurationSection {

    private AncapPacketBuilderSource source;

    public AncapPacketConfigurationSection(AncapPlaceholderedConfigurationSection section, AncapPacketBuilderSource packetBuilderSource) {
        super(section);
        this.source = packetBuilderSource;
    }

    public AncapPacketConfigurationSection(AncapPacketConfigurationSection section) {
        this(section, section.getPacketBuilderSource());
    }

    protected AncapPacketBuilderSource getPacketBuilderSource() {
        return this.source;
    }

    @Override
    public Packet getPacket() throws InvalidConfigurationPacketException {
        AncapPacketBuilder builder = source.getPacketBuilder();
        builder.addMessage(this.getStringList("messages"));
        try {
            builder.setSound(this.getString("sound"));
        } catch (NoSuchElementException ignored) {}
        try {
            builder.setActionBar(this.getString("action_bar"));
        } catch (NoSuchElementException ignored) {}
        try {
            builder.setTitle(this.getString("title"));
        } catch (NoSuchElementException ignored) {}
        return builder.build();
    }
}
