package ru.ancap.framework.plugin.api.packet.api.packet.modules.title.builder;

import ru.ancap.framework.plugin.api.packet.api.packet.modules.title.Title;
import ru.ancap.misc.preparable.Preparable;

public interface PreTitle extends Preparable {

    Title getPrepared();
}
