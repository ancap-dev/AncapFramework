package ru.ancap.framework.plugin.api.configuration.extended.entensions.impl;

import org.bukkit.configuration.ConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.AncapWrappedConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.api.SendableConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.exceptions.InvalidConfigurationSendableException;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.bossbar.BossBar;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.bossbar.builder.BossBarBuilder;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.bossbar.builder.BossBarBuilderSource;

public class AncapBossBarConfigurationSection extends AncapWrappedConfigurationSection implements SendableConfigurationSection {

    private BossBarBuilderSource bossBarBuilderSource;

    public AncapBossBarConfigurationSection(AncapWrappedConfigurationSection section, BossBarBuilderSource bossBarBuilderSource) {
        super(section);
        this.bossBarBuilderSource = bossBarBuilderSource;
    }

    public AncapBossBarConfigurationSection(AncapBossBarConfigurationSection section) {
        this(section, section.getBossBarBuilderSource());
    }

    protected BossBarBuilderSource getBossBarBuilderSource() {
        return this.bossBarBuilderSource;
    }

    @Override
    public BossBar getSendable() throws InvalidConfigurationSendableException {
        BossBarBuilder bossBarBuilder = this.bossBarBuilderSource.getBossBarBuilder();
        ConfigurationSection section = this.getSection();
        String pathName = "name";
        if (section.isSet(pathName)) {
            String title = section.getString(pathName);
            bossBarBuilder.setName(title);
        }
        String pathColor = "color";
        if (this.getSection().isSet(pathColor)) {
            String color = section.getString(pathColor);
            try {
                net.kyori.adventure.bossbar.BossBar.Color.valueOf(color.toUpperCase());
            } catch (RuntimeException e) {
                throw new InvalidConfigurationSendableException(section.getCurrentPath(), color+" is not a color");
            }
            bossBarBuilder.setColorData(color);
        }
        String pathProgress = "progress";
        if (this.getSection().isSet(pathProgress)) {
            String progress = section.getString(pathProgress);
            try {
                bossBarBuilder.setProgress(Float.parseFloat(progress));
            } catch (NumberFormatException e) {
                throw new InvalidConfigurationSendableException(section.getCurrentPath(), progress+" is not a float");
            }
        }
        String pathNotches = "notches";
        if (this.getSection().isSet(pathNotches)) {
            String notches = section.getString(pathNotches);
            try {
                net.kyori.adventure.bossbar.BossBar.Overlay.valueOf(notches.toUpperCase());
            } catch (RuntimeException e) {
                throw new InvalidConfigurationSendableException(section.getCurrentPath(), notches+" is not a notches-data");
            }
            bossBarBuilder.setNotchesData(notches);
        }
        String pathDuration = "duration";
        if (this.getSection().isSet(pathDuration)) {
            String duration = section.getString(pathDuration);
            try {
                bossBarBuilder.setDuration(Integer.parseInt(duration));
            } catch (NumberFormatException e) {
                throw new InvalidConfigurationSendableException(section.getCurrentPath(), duration+" is not a integer");
            }
        }
        return bossBarBuilder.build();
    }
}
