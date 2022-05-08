package ru.ancap.framework.plugin.api.packet.api.packet.modules.actionbar.builder;

public class ActionBarBuilderSourceImpl implements ActionBarBuilderSource {

    @Override
    public ActionBarBuilder getActionBarBuilder() {
        return new ActionBarBuilderImpl();
    }
}
