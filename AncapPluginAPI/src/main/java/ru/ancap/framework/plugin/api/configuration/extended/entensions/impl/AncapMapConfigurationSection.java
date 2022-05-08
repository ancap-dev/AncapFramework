package ru.ancap.framework.plugin.api.configuration.extended.entensions.impl;

import org.bukkit.configuration.ConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.AncapWrappedConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.api.MapConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.exceptions.InvalidConfigurationMapException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AncapMapConfigurationSection extends AncapWrappedConfigurationSection implements MapConfigurationSection {

    public AncapMapConfigurationSection(ConfigurationSection section) {
        super(section);
    }

    public AncapMapConfigurationSection(AncapWrappedConfigurationSection section) {
        super(section);
    }

    @Override
    public Map<String, String> getMap() throws InvalidConfigurationMapException {
        Map<String, String> map = new HashMap<>();
        ConfigurationSection section = this.getSection();
        Set<String> keys = section.getKeys(false);
        this.validateEmpty(keys, section);
        this.fillMap(map, keys, section);
        return map;
    }

    private void fillMap(Map<String, String> map, Set<String> keys, ConfigurationSection section) throws InvalidConfigurationMapException {
        for (String key : keys) {
            String string = this.getSection().getString(key);
            this.validateNotInternal(string, section);
            map.put(key, section.getString(key));
        }
    }

    private void validateNotInternal(String string, ConfigurationSection section) throws InvalidConfigurationMapException {
        if (string == null || string.startsWith("MemorySection")) {
            throw new InvalidConfigurationMapException(section.getCurrentPath(), "Map can't contain internal sections");
        }
    }

    private void validateEmpty(Set<String> keys, ConfigurationSection section) throws InvalidConfigurationMapException {
        if (keys.isEmpty()) {
            throw new InvalidConfigurationMapException(section.getCurrentPath(), "Map cannot be empty!");
        }
    }
}
