package ru.ancap.framework.plugin.api.configuration.placeholder;

import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import ru.ancap.framework.plugin.api.configuration.bukkitwrapper.AncapBukkitConfigurationSection;
import ru.ancap.misc.placeholder.PlaceholderSource;
import ru.ancap.misc.placeholder.PlaceholderSourceBuilderImpl;

public class AncapPlaceholderedConfigurationSection extends AncapBukkitConfigurationSection implements PlaceholderedConfigurationSection {

    private PlaceholderSource source;

    public AncapPlaceholderedConfigurationSection(ConfigurationSection configurationSection, PlaceholderSource source) {
        super(configurationSection);
        this.source = source;
    }

    public AncapPlaceholderedConfigurationSection(AncapPlaceholderedConfigurationSection configurationSection) {
        this(configurationSection.getBukkitConfigurationSection(), configurationSection.getPlaceholderSource());
    }

    public AncapPlaceholderedConfigurationSection(ConfigurationSection configurationSection) {
        super(configurationSection);
    }

    protected PlaceholderSource getPlaceholderSource() {
        if (this.source == null) {
            throw new IllegalStateException("You have to inject PlaceholderSource to AncapPlaceholderedConfigurationSection, in constructor or later!");
        }
        return this.source;
    }

    @Override
    public void setPlaceholderSource(PlaceholderSource source) {
        this.source = source;
    }

    @Override
    public void addPlaceholderSource(PlaceholderSource source) {
        this.setPlaceholderSource(
                new PlaceholderSourceBuilderImpl()
                        .addSource(this.getPlaceholderSource())
                        .addSource(source)
                        .build()
        );
    }

    @Override
    public @NotNull PlaceholderedConfigurationSection getConfigurationSection(@NotNull String path) {
        ConfigurationSection section = super.getConfigurationSection(path);
        return new AncapPlaceholderedConfigurationSection(section, this.getPlaceholderSource());
    }
}
