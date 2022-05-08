package ru.ancap.framework.plugin.api.packet.api.packet.modules.message.builder;

public class MessageBuilderSourceImpl implements MessageBuilderSource {

    @Override
    public MessageBuilder getMessageBuilder() {
        return new MessageBuilderImpl();
    }
}
