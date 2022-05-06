package ru.ancap.framework.plugin.api.packet.api.packet.modules.actionbar;

import ru.ancap.framework.plugin.api.packet.api.packet.modules.text.Text;
import ru.ancap.framework.plugin.api.packet.api.receiver.PacketReceiver;

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
