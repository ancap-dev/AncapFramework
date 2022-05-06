package ru.ancap.framework.plugin.api.configuration.packet;

import ru.ancap.framework.plugin.api.configuration.exception.InvalidConfigurationPacketException;
import ru.ancap.framework.plugin.api.packetapi.packet.Packet;

public interface PacketConfigurationSection {

    Packet getPacket() throws InvalidConfigurationPacketException;
}
