package ru.ancap.framework.plugin.api.configuration.bukkitwrapper;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationOptions;
import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * Class-wrapping for Configuration. I'd made it because no-one Bukkit implementations supported inheritance by instance class.
 * It might be bad code, but I don't know how to do it better
**/

public class AncapBukkitConfiguration extends AncapBukkitConfigurationSection implements Configuration {

    private Configuration configuration;

    public AncapBukkitConfiguration(ConfigurationSection configurationSection, Configuration configuration) {
        super(configurationSection);
        this.configuration = configuration;
    }

    public AncapBukkitConfiguration(Configuration configuration) {
        this(configuration, configuration);
    }

    public AncapBukkitConfiguration(AncapBukkitConfiguration configuration) {
        this(configuration.getBukkitConfigurationSection(), configuration.getBukkitConfiguration());
    }

    protected Configuration getBukkitConfiguration() {
        return this.configuration;
    }

    @Override
    public void addDefaults(@NotNull Map<String, Object> defaults) {
        this.configuration.addDefaults(defaults);
    }

    @Override
    public void addDefaults(@NotNull Configuration defaults) {
        this.configuration.addDefaults(defaults);
    }

    @Override
    public void setDefaults(@NotNull Configuration defaults) {
        this.configuration.setDefaults(defaults);
    }

    @Nullable
    @Override
    public Configuration getDefaults() {
        return this.configuration.getDefaults();
    }

    @NotNull
    @Override
    public ConfigurationOptions options() {
        return this.configuration.options();
    }
}
