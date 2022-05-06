package ru.ancap.framework.plugin.api.packetapi.packet.builder;

public class AncapPacketBuilderSourceImpl implements AncapPacketBuilderSource {

    @Override
    public AncapPacketBuilder getPacketBuilder() {
        return new AncapPacketBuilderImpl();
    }
}
