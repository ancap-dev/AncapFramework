package ru.ancap.framework.plugin.api.packetapi.packet.modules.text.builder;

import ru.ancap.framework.plugin.api.packetapi.packet.modules.text.Text;

public interface TextFactory {

    Text buildFrom(String string);

}
