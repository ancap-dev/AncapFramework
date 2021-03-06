package ru.ancap.framework.plugin.configuration;

import org.bukkit.configuration.ConfigurationSection;
import ru.ancap.framework.api.language.Language;

public class AncapPluginConfig {

    private static AncapPluginConfig loaded;

    private final ConfigurationSection section;

    public AncapPluginConfig(ConfigurationSection section) {
        this.section = section;
    }

    public void load() {
        loaded = this;
    }

    public static AncapPluginConfig loaded() {
        return loaded;
    }

    public Language defaultLanguage() {
        return Language.of(this.section.getString("default-language"));
    }

    public String getDatabaseDataAccessKey() {
        return this.section.getString("database.connection.type").toLowerCase();
    }

    public ConfigurationSection getDatabaseDriverDataSection() {
        return this.section.getConfigurationSection("database.data");
    }

    public ConfigurationSection getDatabaseConnectionSection() {
        return this.section.getConfigurationSection("database.connection");
    }

}
