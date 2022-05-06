package ru.ancap.framework.plugin.api.packet.api.packet.builder;

public class AncapPacketBuilderSourceImpl implements AncapPacketBuilderSource {

    @Override
    public AncapPacketBuilder getPacketBuilder() {
        return new AncapPacketBuilderImpl();
    }
}
