package ru.ancap.framework.plugin.api.packet.api.packet.builder;

public class PacketBuilderSourceImpl implements PacketBuilderSource {

    @Override
    public PacketBuilder getPacketBuilder() {
        return new PacketBuilderImpl();
    }
}
