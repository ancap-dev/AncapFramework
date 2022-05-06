package ru.ancap.framework.plugin.api.packetapi.packet.builder;

import ru.ancap.framework.plugin.api.packetapi.packet.modules.text.builder.TextFactory;

public interface AncapPacketBuilder extends PacketBuilder {

    AncapPacketBuilder setCustomTextFactory(TextFactory factory);
}
