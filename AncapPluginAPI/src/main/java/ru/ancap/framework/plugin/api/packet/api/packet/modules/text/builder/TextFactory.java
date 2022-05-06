package ru.ancap.framework.plugin.api.packet.api.packet.modules.text.builder;

import ru.ancap.framework.plugin.api.packet.api.packet.modules.text.Text;

public interface TextFactory {

    Text buildFrom(String string);

}
