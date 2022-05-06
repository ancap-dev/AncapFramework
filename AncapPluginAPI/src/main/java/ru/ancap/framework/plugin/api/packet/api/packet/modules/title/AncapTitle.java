package ru.ancap.framework.plugin.api.packet.api.packet.modules.title;

import ru.ancap.framework.plugin.api.packet.api.packet.modules.text.Text;
import ru.ancap.framework.plugin.api.packet.api.receiver.PacketReceiver;

public class AncapTitle implements Title {

    private Text text;
    private Text subText;
    private int fadeIn;
    private int time;
    private int fadeOut;

    public AncapTitle(Text text, Text subText, int fadeIn, int time, int fadeOut) {
        this.text = text;
        this.subText = subText;
        this.fadeIn = fadeIn;
        this.time = time;
        this.fadeOut = fadeOut;
    }

    public AncapTitle(AncapTitle title) {
        this(title.getText(), title.getSubText(), title.getFadeIn(), title.getTime(), title.getFadeOut());
    }

    protected Text getText() {
        return this.text;
    }

    protected Text getSubText() {
        return this.subText;
    }

    protected int getFadeIn() {
        return this.fadeIn;
    }

    protected int getTime() {
        return this.time;
    }

    protected int getFadeOut() {
        return this.fadeOut;
    }

    @Override
    public void sendTo(PacketReceiver receiveable) {
        receiveable.sendTitle(this.getText().getString(), this.getSubText().getString(), this.getFadeIn(), this.getTime(), this.getFadeOut());
    }
}
