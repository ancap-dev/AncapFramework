package ru.ancap.framework.api.plugin.plugins.info;

import org.bukkit.configuration.ConfigurationSection;
import ru.ancap.misc.strings.integer.AncapIntegerString;

public class AncapPluginSettings {

    private ConfigurationSection section;

    protected static class Path {
        public static final String PLUGIN_ID = "plugin_id";
    }

    public AncapPluginSettings(ConfigurationSection configuration) {
        this.section = configuration;
    }

    public int getPluginId() {
        String string = this.section.getString(Path.PLUGIN_ID);
        return new AncapIntegerString(string).getValue();
    }

}
