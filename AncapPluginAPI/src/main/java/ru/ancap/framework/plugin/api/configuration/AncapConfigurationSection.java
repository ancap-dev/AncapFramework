package ru.ancap.framework.plugin.api.configuration;

import org.bukkit.configuration.ConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.bukkitwrapper.AncapBukkitConfigurationSection;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * It's highly recommended using only AncapConfigurationSection#getString/getStringList/getMap method to get data
 * from config, and to convert it to normal objects after getting it from file
 */

public class AncapConfigurationSection extends AncapBukkitConfigurationSection {

    public AncapConfigurationSection(ConfigurationSection configurationSection) {
        super(configurationSection);
    }

    public Map<String, String> getMap() {
        Map<String, String> map = new HashMap<>();
        Set<String> keys = this.getKeys(false);
        keys.forEach(key -> map.put(key, this.getString(key)));
        return map;
    }

}
