package ru.ancap.framework.plugin.api.packet.api.packet.modules.text.builder;

import ru.ancap.framework.plugin.api.packet.api.packet.modules.chatcolor.AncapChatColorFormattableString;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.chatcolor.ChatColorFormattableString;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.text.AncapText;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.text.PreText;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.text.Text;
import ru.ancap.misc.strings.AncapStringObject;

public class AncapPreText extends AncapStringObject implements PreText {

    public AncapPreText(String string) {
        super(string);
    }

    @Override
    public Text getPrepared() {
        ChatColorFormattableString formattable = new AncapChatColorFormattableString(this.getString());
        formattable.format();
        return new AncapText(formattable.getString());
    }
}
