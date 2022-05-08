package ru.ancap.framework.plugin.api.packet.api.packet.modules.title.builder;

import ru.ancap.framework.plugin.api.packet.api.packet.modules.title.Title;

public interface TitleBuilder {

    TitleBuilder setTitle(String title);
    TitleBuilder setSubTitle(String subTitle);
    TitleBuilder setFadeIn(int fadeIn);
    TitleBuilder setTime(int time);
    TitleBuilder setFadeOut(int fadeOut);

    Title build();
}
