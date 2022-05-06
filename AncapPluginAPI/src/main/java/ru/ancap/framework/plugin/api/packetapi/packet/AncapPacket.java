package ru.ancap.framework.plugin.api.packetapi.packet;

import ru.ancap.framework.plugin.api.packetapi.receiver.PacketReceiver;

import java.util.List;

public class AncapPacket implements Packet {

    private List<Sendable> sendables;

    public AncapPacket(List<Sendable> sendables) {
        this.sendables = sendables;
    }

    public AncapPacket(AncapPacket packet) {
        this.sendables = packet.getSendables();
    }

    protected List<Sendable> getSendables() {
        return this.sendables;
    }

    public void sendTo(PacketReceiver packetReceiveable) {
        this.sendables.forEach(sendable -> sendable.sendTo(packetReceiveable));
    }
}
