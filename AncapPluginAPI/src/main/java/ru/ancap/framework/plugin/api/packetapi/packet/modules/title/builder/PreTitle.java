package ru.ancap.framework.plugin.api.packetapi.packet.modules.title.builder;

import ru.ancap.framework.plugin.api.packetapi.packet.modules.title.Title;
import ru.ancap.misc.preparable.Preparable;

public interface PreTitle extends Preparable {

    Title getPrepared();
}
