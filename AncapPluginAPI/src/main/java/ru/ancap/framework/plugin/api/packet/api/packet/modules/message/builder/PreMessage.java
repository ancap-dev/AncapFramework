package ru.ancap.framework.plugin.api.packet.api.packet.modules.message.builder;

import ru.ancap.framework.plugin.api.packet.api.packet.modules.message.Message;
import ru.ancap.misc.preparable.Preparable;
import ru.ancap.misc.preparable.exception.PreparationException;

public interface PreMessage extends Preparable {

    Message getPrepared() throws PreparationException;
}
