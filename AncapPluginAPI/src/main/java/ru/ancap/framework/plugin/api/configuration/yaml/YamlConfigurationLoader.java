package ru.ancap.framework.plugin.api.configuration.yaml;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.Reader;

public class YamlConfigurationLoader {

    private Reader reader;

    public YamlConfigurationLoader(Reader reader) {
        this.reader = reader;
    }

    public YamlConfiguration load() {
        return YamlConfiguration.loadConfiguration(reader);
    }
}
