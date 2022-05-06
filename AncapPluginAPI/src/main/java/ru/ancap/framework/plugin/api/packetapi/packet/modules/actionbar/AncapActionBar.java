package ru.ancap.framework.plugin.api.packetapi.packet.modules.actionbar;

import ru.ancap.framework.plugin.api.packetapi.packet.modules.text.Text;
import ru.ancap.framework.plugin.api.packetapi.receiver.PacketReceiver;

public class AncapActionBar implements ActionBar {

    private Text text;

    public AncapActionBar(Text text) {
        this.text = text;
    }

    public AncapActionBar(AncapActionBar bar) {
        this(bar.getText());
    }

    protected Text getText() {
        return text;
    }

    @Override
    public void sendTo(PacketReceiver receiveable) {
        receiveable.sendActionBar(this.getText().getString());
    }
}
