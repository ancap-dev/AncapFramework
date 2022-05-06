package ru.ancap.framework.plugin.api.configuration.packet;

import ru.ancap.framework.plugin.api.configuration.AncapPlaceholderedConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.exception.InvalidConfigurationPacketException;
import ru.ancap.framework.plugin.api.packetapi.packet.Packet;
import ru.ancap.framework.plugin.api.packetapi.packet.builder.AncapPacketBuilder;
import ru.ancap.framework.plugin.api.packetapi.packet.builder.AncapPacketBuilderSource;

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
        builder.addMessage(this.getStringList("message"));
        builder.setSound(this.getString("sound"));
        builder.setActionBar(this.getString("action_bar"));
        builder.setTitle(this.getString("title"));
        return builder.build();
    }
}
