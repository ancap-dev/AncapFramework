package ru.ancap.framework.plugin.api.packet.api.packet.builder;

import ru.ancap.framework.plugin.api.packet.api.packet.Packet;
import ru.ancap.framework.plugin.api.packet.api.packet.Sendable;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.actionbar.ActionBar;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.bossbar.BossBar;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.message.Message;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.sound.Sound;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.title.Title;

public interface PacketBuilder {
    PacketBuilder addMessage(Message message);
    PacketBuilder setSound(Sound sound);
    PacketBuilder setTitle(Title title);
    PacketBuilder setActionBar(ActionBar actionBar);
    PacketBuilder setBossBar(BossBar bossBar);
    PacketBuilder addSendable(Sendable sendable);
    Packet build();
}
