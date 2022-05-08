package ru.ancap.framework.plugin.api.packet.api.packet.modules.title.builder;

public class TitleBuilderSourceImpl implements TitleBuilderSource {

    @Override
    public TitleBuilder getTitleBuilder() {
        return new TitleBuilderImpl();
    }
}
