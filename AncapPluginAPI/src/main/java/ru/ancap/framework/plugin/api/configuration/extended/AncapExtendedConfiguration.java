package ru.ancap.framework.plugin.api.configuration.extended;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationOptions;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class AncapExtendedConfiguration extends AncapExtendedConfigurationSection implements Configuration {

    private Configuration configuration;

    public AncapExtendedConfiguration(AncapExtendedConfigurationSection section, Configuration configuration) {
        super(section);
        this.configuration = configuration;
    }

    public AncapExtendedConfiguration(AncapExtendedConfiguration configuration) {
        super(configuration);
        this.configuration = configuration.getWrappedConfiguration();
    }

    protected Configuration getWrappedConfiguration() {
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

    @Override
    public @Nullable Configuration getDefaults() {
        return this.configuration.getDefaults();
    }

    @Override
    public @NotNull ConfigurationOptions options() {
        return this.configuration.options();
    }
}
