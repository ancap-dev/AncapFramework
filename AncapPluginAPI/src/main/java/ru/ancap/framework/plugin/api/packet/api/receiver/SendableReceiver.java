package ru.ancap.framework.plugin.api.packet.api.receiver;

import ru.ancap.framework.plugin.api.packet.api.packet.Sendable;

public interface SendableReceiver extends SoundReceiver, TextReceiveable, TitleReceiver, ActionBarReceiver, BossBarReceiver {

    void send(Sendable sendable);
}
