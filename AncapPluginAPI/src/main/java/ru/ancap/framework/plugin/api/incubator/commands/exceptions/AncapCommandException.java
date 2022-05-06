package ru.ancap.framework.plugin.api.incubator.commands.exceptions;

import ru.ancap.framework.plugin.api.packet.api.packet.Sendable;
import ru.ancap.framework.plugin.api.packet.api.receiver.PacketReceiver;

public abstract class AncapCommandException extends HandleableException {

    private PacketReceiver packetReceiver;

    public AncapCommandException(PacketReceiver receiver) {
        this.packetReceiver = receiver;
    }

    public AncapCommandException(AncapCommandException exception) {
        this(exception.getPacketReceiver());
    }

    protected PacketReceiver getPacketReceiver() {
        return this.packetReceiver;
    }

    @Override
    public void handle() {
        Sendable sendable = this.getErrorNotifier();
        this.getPacketReceiver().send(sendable);
    }

    public abstract Sendable getErrorNotifier();
}
