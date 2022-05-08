package ru.ancap.framework.plugin.api.packet.api.packet.modules.message.builder;

import ru.ancap.framework.plugin.api.packet.api.packet.modules.message.AncapMessage;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.message.Message;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.text.Text;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.text.builder.ColorizedTextFactory;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.text.builder.TextFactory;

import java.util.ArrayList;
import java.util.List;

public class MessageBuilderImpl implements MessageBuilder {

    private TextFactory textFactory;

    private List<String> strings;

    public MessageBuilderImpl() {
        this(new ColorizedTextFactory());
    }

    public MessageBuilderImpl(TextFactory factory) {
        this.textFactory = factory;
        this.strings = new ArrayList<>();
    }

    public MessageBuilderImpl(MessageBuilderImpl messageBuilder) {
        this(messageBuilder.getTextFactory());
    }

    protected TextFactory getTextFactory() {
        return this.textFactory;
    }

    @Override
    public MessageBuilder addText(String text) {
        this.strings.add(text);
        return this;
    }

    public MessageBuilder setTexts(List<String> strings) {
        this.strings = strings;
        return this;
    }

    @Override
    public Message build() {
        List<Text> texts = new ArrayList<>();
        strings.forEach(str -> texts.add(this.getTextFactory().buildFrom(str)));
        return new AncapMessage(texts);
    }
}
