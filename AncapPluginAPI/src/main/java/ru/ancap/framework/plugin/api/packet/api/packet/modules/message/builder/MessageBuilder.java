package ru.ancap.framework.plugin.api.packet.api.packet.modules.message.builder;

import ru.ancap.framework.plugin.api.packet.api.packet.modules.message.Message;

import java.util.List;

public interface MessageBuilder {

    MessageBuilder addText(String string);
    MessageBuilder setTexts(List<String> stringList);

    Message build();
}
