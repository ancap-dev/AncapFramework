package ru.ancap.framework.plugin.configuration;

import org.bukkit.configuration.ConfigurationSection;
import ru.ancap.framework.api.language.Language;

public class AncapPluginConfig {

    private static AncapPluginConfig loaded;

    private final ConfigurationSection section;

    public AncapPluginConfig(ConfigurationSection section) {
        this.section = section;
    }

    public static AncapPluginConfig loaded() {
        return loaded;
    }

    public Language defaultLanguage() {
        return Language.of(this.section.getString("default-language"));
    }

}
