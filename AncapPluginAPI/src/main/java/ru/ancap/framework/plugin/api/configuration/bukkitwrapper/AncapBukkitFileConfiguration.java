package ru.ancap.framework.plugin.api.configuration.bukkitwrapper;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import ru.ancap.framework.plugin.api.configuration.bukkitwrapper.AncapBukkitConfiguration;

public class AncapBukkitFileConfiguration extends AncapBukkitConfiguration implements Configuration {

    private FileConfiguration fileConfiguration;

    public AncapBukkitFileConfiguration(AncapBukkitConfiguration configuration, FileConfiguration fileConfiguration) {
        super(configuration, fileConfiguration);
    }

    protected FileConfiguration getFileConfiguration() {
        return this.fileConfiguration;
    }
}
