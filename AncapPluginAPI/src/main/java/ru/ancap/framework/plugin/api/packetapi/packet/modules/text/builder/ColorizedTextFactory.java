package ru.ancap.framework.plugin.api.packetapi.packet.modules.text.builder;

import ru.ancap.framework.plugin.api.packetapi.packet.modules.text.Text;

public class ColorizedTextFactory implements TextFactory {

    @Override
    public Text buildFrom(String string) {
        return new AncapPreText(string).getPrepared();
    }
}
