package ru.ancap.framework.plugin.api.packet.api.packet.modules.actionbar.builder;

import ru.ancap.framework.plugin.api.packet.api.packet.modules.actionbar.ActionBar;
import ru.ancap.misc.preparable.Preparable;
import ru.ancap.misc.preparable.exception.PreparationException;

public interface PreActionBar extends Preparable {

    ActionBar getPrepared() throws PreparationException;
}
