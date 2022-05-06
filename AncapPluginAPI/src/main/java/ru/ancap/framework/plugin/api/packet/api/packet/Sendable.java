package ru.ancap.framework.plugin.api.packet.api.packet;

import ru.ancap.framework.plugin.api.packet.api.receiver.PacketReceiver;

public interface Sendable {

    void sendTo(PacketReceiver receiveable);

}
