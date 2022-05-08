package ru.ancap.framework.plugin.api.packet.api.packet.modules.title.builder;

import ru.ancap.framework.plugin.api.packet.api.packet.modules.text.builder.ColorizedTextFactory;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.text.builder.TextFactory;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.title.AncapTitle;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.title.Title;

public class TitleBuilderImpl implements TitleBuilder {

    private TextFactory textFactory;

    private String title = "";
    private String subTitle = "";
    private int fadeIn = 1;
    private int time = 1;
    private int fadeOut = 1;

    public TitleBuilderImpl() {
        this(new ColorizedTextFactory());
    }

    public TitleBuilderImpl(TextFactory factory) {
        this.textFactory = factory;
    }

    public TitleBuilderImpl(TitleBuilderImpl titleBuilder) {
        this(titleBuilder.getTextFactory());
    }

    protected TextFactory getTextFactory() {
        return this.textFactory;
    }

    @Override
    public TitleBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    @Override
    public TitleBuilder setSubTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    @Override
    public TitleBuilder setFadeIn(int fadeIn) {
        this.fadeIn = fadeIn;
        return this;
    }

    @Override
    public TitleBuilder setTime(int time) {
        this.time = time;
        return this;
    }

    @Override
    public TitleBuilder setFadeOut(int fadeOut) {
        this.fadeOut = fadeOut;
        return this;
    }

    @Override
    public Title build() {
        return new AncapTitle(
                this.getTextFactory().buildFrom(title),
                this.getTextFactory().buildFrom(subTitle),
                fadeIn,
                time,
                fadeOut
        );
    }
}
