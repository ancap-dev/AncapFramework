package ru.ancap.framework.plugin.api.configuration.placeholder;

import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import ru.ancap.misc.placeholder.PlaceholderSource;

public interface PlaceholderedConfigurationSection extends ConfigurationSection {

    void setPlaceholderSource(PlaceholderSource source);
    void addPlaceholderSource(PlaceholderSource source);
    @Override
    PlaceholderedConfigurationSection getConfigurationSection(@NotNull String path);
}
