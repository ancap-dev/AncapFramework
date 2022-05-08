package ru.ancap.framework.plugin.api.packet.api.packet.modules.actionbar.builder;

import ru.ancap.framework.plugin.api.packet.api.packet.modules.actionbar.ActionBar;

public interface ActionBarBuilder {

    ActionBarBuilder setText(String text);

    ActionBar build();
}
