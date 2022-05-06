package ru.ancap.framework.plugin.api.packet.api.packet.modules.text.builder;

import ru.ancap.framework.plugin.api.packet.api.packet.modules.text.Text;

public class ColorizedTextFactory implements TextFactory {

    @Override
    public Text buildFrom(String string) {
        return new AncapPreText(string).getPrepared();
    }
}
