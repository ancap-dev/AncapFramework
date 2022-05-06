package ru.ancap.framework.plugin.api.packetapi.packet;

import ru.ancap.framework.plugin.api.packetapi.receiver.PacketReceiver;

public interface Sendable {

    void sendTo(PacketReceiver receiveable);

}
