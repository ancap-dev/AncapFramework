package ru.ancap.framework.plugin.api.configuration;

import org.bukkit.configuration.ConfigurationSection;

public class AncapWrappedConfigurationSection {

    private ConfigurationSection section;

    public AncapWrappedConfigurationSection(ConfigurationSection section) {
        this.section = section;
    }

    public AncapWrappedConfigurationSection(AncapWrappedConfigurationSection section) {
        this(section.getSection());
    }

    protected ConfigurationSection getSection() {
        return this.section;
    }
}
