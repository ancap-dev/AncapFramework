package ru.ancap.framework.plugin.api.packet.api.packet.builder;

import ru.ancap.framework.plugin.api.packet.api.packet.Packet;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.actionbar.ActionBar;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.bossbar.BossBar;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.message.Message;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.sound.Sound;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.text.Text;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.title.Title;

import java.util.List;

public interface PacketBuilder {

    PacketBuilder addMessage(String text);
    PacketBuilder addMessage(Text text);
    PacketBuilder addMessage(String[] strings);
    PacketBuilder addMessage(List<String> strings);
    PacketBuilder addMessage(Message message);
    PacketBuilder setSound(String name);
    PacketBuilder setSound(String name, float volume, float pitch);
    PacketBuilder setSound(Sound sound);
    PacketBuilder setTitle(String string);
    PacketBuilder setTitle(String string, String subTitle);
    PacketBuilder setTitle(Text text);
    PacketBuilder setTitle(Text title, Text subTitle);
    PacketBuilder setTitle(Text title, Text subTitle, int fadeIn, int time, int fadeOut);
    PacketBuilder setTitle(Title title);
    PacketBuilder setActionBar(String string);
    PacketBuilder setActionBar(Text text);
    PacketBuilder setActionBar(ActionBar actionBar);
    PacketBuilder setBossBar(BossBar bossBar);

    Packet build();
}
