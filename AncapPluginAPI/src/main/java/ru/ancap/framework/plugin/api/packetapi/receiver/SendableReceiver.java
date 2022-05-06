package ru.ancap.framework.plugin.api.packetapi.receiver;

import ru.ancap.framework.plugin.api.packetapi.packet.Sendable;

public interface SendableReceiver extends SoundReceiver, TextReceiveable, TitleReceiver, ActionBarReceiver, BossBarReceiver {

    void send(Sendable sendable);
}
