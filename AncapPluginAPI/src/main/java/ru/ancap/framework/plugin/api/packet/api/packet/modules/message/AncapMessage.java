package ru.ancap.framework.plugin.api.packet.api.packet.modules.message;

import ru.ancap.framework.plugin.api.packet.api.packet.modules.text.Text;
import ru.ancap.framework.plugin.api.packet.api.receiver.PacketReceiver;

import java.util.List;

public class AncapMessage implements Message {

    private List<Text> messages;

    public AncapMessage(List<Text> messages) {
        this.messages = messages;
    }

    public AncapMessage(AncapMessage message) {
        this(message.getMessages());
    }

    protected List<Text> getMessages() {
        return this.messages;
    }

    @Override
    public void sendTo(PacketReceiver receiveable) {
        this.getMessages().forEach(message -> receiveable.sendString(message.getString()));
    }
}
