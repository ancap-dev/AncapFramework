package ru.ancap.framework.plugin.api.configuration.extended.entensions.impl;

import org.bukkit.configuration.ConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.AncapWrappedConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.api.SendableConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.exceptions.InvalidConfigurationSendableException;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.title.Title;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.title.builder.TitleBuilder;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.title.builder.TitleBuilderSource;

public class AncapTitleConfigurationSection extends AncapWrappedConfigurationSection implements SendableConfigurationSection {

    private TitleBuilderSource titleBuilderSource;

    public AncapTitleConfigurationSection(AncapWrappedConfigurationSection section, TitleBuilderSource titleBuilderSource) {
        super(section);
        this.titleBuilderSource = titleBuilderSource;
    }

    public AncapTitleConfigurationSection(AncapTitleConfigurationSection section) {
        this(section, section.getTitleBuilderSource());
    }

    protected TitleBuilderSource getTitleBuilderSource() {
        return this.titleBuilderSource;
    }

    @Override
    public Title getSendable() throws InvalidConfigurationSendableException {
        TitleBuilder titleBuilder = this.titleBuilderSource.getTitleBuilder();
        ConfigurationSection section = this.getSection();
        String pathTitle = "title";
        if (section.isSet(pathTitle)) {
            String title = section.getString(pathTitle);
            titleBuilder.setTitle(title);
        }
        String pathSubtitle = "subtitle";
        if (this.getSection().isSet(pathSubtitle)) {
            String subTitle = section.getString(pathSubtitle);
            titleBuilder.setSubTitle(subTitle);
        }
        String pathFadeIn = "fadein";
        if (this.getSection().isSet(pathFadeIn)) {
            String fadeIn = section.getString(pathFadeIn);
            try {
                titleBuilder.setFadeIn(Integer.parseInt(fadeIn));
            } catch (NumberFormatException e) {
                throw new InvalidConfigurationSendableException(section.getCurrentPath(), fadeIn+" is not a integer");
            }
        }
        String pathTime = "time";
        if (this.getSection().isSet(pathTime)) {
            String time = section.getString(pathTime);
            try {
                titleBuilder.setTime(Integer.parseInt(time));
            } catch (NumberFormatException e) {
                throw new InvalidConfigurationSendableException(section.getCurrentPath(), time+" is not a integer");
            }
        }
        String pathFadeOut = "fadeout";
        if (this.getSection().isSet(pathFadeOut)) {
            String fadeOut = section.getString(pathFadeOut);
            try {
                titleBuilder.setFadeOut(Integer.parseInt(fadeOut));
            } catch (NumberFormatException e) {
                throw new InvalidConfigurationSendableException(section.getCurrentPath(), fadeOut+" is not a integer");
            }
        }
        return titleBuilder.build();
    }
}
