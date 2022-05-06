package ru.ancap.framework.plugin.api.packet.api.packet.builder;

import ru.ancap.framework.plugin.api.packet.api.packet.modules.text.builder.TextFactory;

public interface AncapPacketBuilder extends PacketBuilder {

    AncapPacketBuilder setCustomTextFactory(TextFactory factory);
}
