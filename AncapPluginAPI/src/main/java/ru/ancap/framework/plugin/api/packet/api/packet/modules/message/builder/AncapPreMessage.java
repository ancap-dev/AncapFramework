package ru.ancap.framework.plugin.api.packet.api.packet.modules.message.builder;

import ru.ancap.framework.plugin.api.packet.api.packet.modules.message.AncapMessage;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.message.Message;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.text.Text;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.text.builder.AncapPreText;

import java.util.ArrayList;
import java.util.List;

public class AncapPreMessage implements PreMessage {

    private List<String> strings;

    public AncapPreMessage(List<String> strings) {
        this.strings = strings;
    }

    public AncapPreMessage(AncapPreMessage message) {
        this(message.getStrings());
    }

    protected List<String> getStrings() {
        return this.strings;
    }

    @Override
    public Message getPrepared() {
        List<Text> texts = new ArrayList<>();
        strings.forEach((str -> texts.add(new AncapPreText(str).getPrepared())));
        return new AncapMessage(texts);
    }
}
