package ru.ancap.framework.api.plugin.plugins.info;

import org.bukkit.configuration.ConfigurationSection;

import java.util.Collections;
import java.util.List;

public class AncapPluginSettings {

    private final ConfigurationSection section;

    protected static class Path {
        public static final String PLUGIN_ID = "id";
        public static final String COMMAND_LIST = "commands.list";
        public static final String ALIASES_DOMAIN = "commands.aliases";
    }

    public AncapPluginSettings(ConfigurationSection configuration) {
        this.section = configuration;
    }

    public int getPluginId() {
        return this.section.getInt(Path.PLUGIN_ID);
    }

    public List<String> getCommandList() {
        return this.section.getStringList(Path.COMMAND_LIST);
    }

    public List<String> getAliasesList(String commandName) {
        ConfigurationSection section = this.section.getConfigurationSection(Path.ALIASES_DOMAIN);
        if (section == null) return Collections.emptyList();
        return section.getStringList(commandName);
    }

}
