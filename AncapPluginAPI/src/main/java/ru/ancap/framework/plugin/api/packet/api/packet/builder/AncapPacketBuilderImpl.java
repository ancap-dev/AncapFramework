package ru.ancap.framework.plugin.api.packet.api.packet.builder;

import ru.ancap.framework.plugin.api.packet.api.packet.AncapPacket;
import ru.ancap.framework.plugin.api.packet.api.packet.Packet;
import ru.ancap.framework.plugin.api.packet.api.packet.Sendable;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.actionbar.ActionBar;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.actionbar.AncapActionBar;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.bossbar.BossBar;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.message.AncapMessage;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.message.Message;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.sound.AncapSound;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.sound.Sound;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.text.Text;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.text.builder.ColorizedTextFactory;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.text.builder.TextFactory;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.title.AncapTitle;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.title.Title;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AncapPacketBuilderImpl implements AncapPacketBuilder {

    private TextFactory textFactory;

    private List<Sendable> packetComponents;

    private List<Message> messages;
    private Sound sound;
    private Title title;
    private ActionBar actionBar;
    private BossBar bossBar;

    public AncapPacketBuilderImpl() {
        this(
                new ColorizedTextFactory()
        );
    }

    public AncapPacketBuilderImpl(TextFactory textFactory) {
        this.packetComponents = new ArrayList<>();
        this.messages = new ArrayList<>();

        this.textFactory = textFactory;
    }

    public AncapPacketBuilderImpl(AncapPacketBuilderImpl builder) {
        this(builder.getTextFactory());
    }

    protected TextFactory getTextFactory() {
        return this.textFactory;
    }

    @Override
    public AncapPacketBuilder setCustomTextFactory(TextFactory factory) {
        this.textFactory = factory;
        return this;
    }

    @Override
    public AncapPacketBuilder addMessage(String text) {
        this.addMessage(List.of(text));
        return this;
    }

    @Override
    public AncapPacketBuilder addMessage(Text text) {
        this.addMessage(new AncapMessage(List.of(text)));
        return this;
    }

    @Override
    public AncapPacketBuilder addMessage(String[] strings) {
        List<String> list = Arrays.asList(strings);
        this.addMessage(list);
        return this;
    }

    @Override
    public AncapPacketBuilder addMessage(List<String> strings) {
        List<Text> list = new ArrayList<>();
        strings.forEach(string -> list.add(this.textFactory.buildFrom(string)));
        Message message = new AncapMessage(list);
        this.addMessage(message);
        return this;
    }

    @Override
    public AncapPacketBuilder addMessage(Message message) {
        messages.add(message);
        return this;
    }

    @Override
    public AncapPacketBuilder setSound(String name) {
        Sound sound = new AncapSound(name, 1f, 1f);
        this.setSound(sound);
        return this;
    }

    @Override
    public AncapPacketBuilder setSound(String name, float volume, float pitch) {
        Sound sound = new AncapSound(name, volume, pitch);
        this.setSound(sound);
        return this;
    }

    @Override
    public AncapPacketBuilder setSound(Sound sound) {
        this.sound = sound;
        return this;
    }

    @Override
    public AncapPacketBuilder setTitle(String string) {
        this.setTitle(string, "");
        return this;
    }

    @Override
    public AncapPacketBuilder setTitle(String string, String subTitle) {
        Text text = this.textFactory.buildFrom(string);
        Text subText = this.textFactory.buildFrom(subTitle);
        this.setTitle(text, subText);
        return this;
    }

    @Override
    public AncapPacketBuilder setTitle(Text text) {
        Text subtext = this.textFactory.buildFrom("");
        this.setTitle(text, subtext);
        return this;
    }

    @Override
    public AncapPacketBuilder setTitle(Text text, Text subText) {
        this.setTitle(text, subText, 1, 1, 1);
        return this;
    }

    @Override
    public AncapPacketBuilder setTitle(Text text, Text subText, int fadeIn, int time, int fadeOut) {
        Title title = new AncapTitle(text, subText, fadeIn, time, fadeOut);
        this.setTitle(title);
        return this;
    }

    @Override
    public AncapPacketBuilder setTitle(Title title) {
        this.title = title;
        return this;
    }

    @Override
    public AncapPacketBuilder setActionBar(String string) {
        Text text = this.textFactory.buildFrom(string);
        this.setActionBar(text);
        return this;
    }

    @Override
    public AncapPacketBuilder setActionBar(Text text) {
        ActionBar actionBar = new AncapActionBar(text);
        this.setActionBar(actionBar);
        return this;
    }

    @Override
    public AncapPacketBuilder setActionBar(ActionBar actionBar) {
        this.actionBar = actionBar;
        return this;
    }

    @Override
    public AncapPacketBuilder setBossBar(BossBar bossBar) {
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

    private void addSendable(Sendable sendable) {
        if (sendable != null) {
            packetComponents.add(sendable);
        }
    }
}
