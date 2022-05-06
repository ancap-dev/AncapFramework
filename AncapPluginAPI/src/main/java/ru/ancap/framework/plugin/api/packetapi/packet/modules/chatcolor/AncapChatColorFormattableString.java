package ru.ancap.framework.plugin.api.packetapi.packet.modules.chatcolor;

import ru.ancap.misc.strings.AncapStringObject;

public class AncapChatColorFormattableString extends AncapStringObject implements ChatColorFormattableString {

    public AncapChatColorFormattableString(String string) {
        super(string);
    }

    @Override
    public void format() {
        this.replace("&", "§");
    }
}
