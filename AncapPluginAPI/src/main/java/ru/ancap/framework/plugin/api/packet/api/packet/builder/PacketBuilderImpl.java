package ru.ancap.framework.plugin.api.packet.api.packet.builder;

import ru.ancap.framework.plugin.api.packet.api.packet.AncapPacket;
import ru.ancap.framework.plugin.api.packet.api.packet.Packet;
import ru.ancap.framework.plugin.api.packet.api.packet.Sendable;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.actionbar.ActionBar;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.bossbar.BossBar;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.message.Message;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.sound.Sound;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.title.Title;

import java.util.ArrayList;
import java.util.List;

public class PacketBuilderImpl implements PacketBuilder {

    private List<Sendable> packetComponents;

    private List<Message> messages;
    private Sound sound;
    private Title title;
    private ActionBar actionBar;
    private BossBar bossBar;

    public PacketBuilderImpl() {
        this.packetComponents = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    @Override
    public PacketBuilder addMessage(Message message) {
        messages.add(message);
        return this;
    }

    @Override
    public PacketBuilder setSound(Sound sound) {
        this.sound = sound;
        return this;
    }

    @Override
    public PacketBuilder setTitle(Title title) {
        this.title = title;
        return this;
    }

    @Override
    public PacketBuilder setActionBar(ActionBar actionBar) {
        this.actionBar = actionBar;
        return this;
    }

    @Override
    public PacketBuilder setBossBar(BossBar bossBar) {
        this.bossBar = bossBar;
        return this;
    }

    @Override
    public Packet build() {
        this.addMessages();
        this.addSendable(this.sound);
        this.addSendable(this.title);
        this.addSendable(this.actionBar);
        this.addSendable(this.bossBar);
        return new AncapPacket(this.packetComponents);
    }

    private void addMessages() {
        if (messages.size() > 0) {
            packetComponents.addAll(this.messages);
        }
    }

    @Override
    public PacketBuilder addSendable(Sendable sendable) {
        if (sendable != null) {
            packetComponents.add(sendable);
        }
        return this;
    }
}
