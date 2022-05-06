package ru.ancap.framework.plugin.api.configuration;

import org.bukkit.configuration.ConfigurationSection;
import org.jetbrains.annotations.NotNull;
import ru.ancap.framework.plugin.api.configuration.exception.BadConfigurationException;
import ru.ancap.framework.plugin.api.configuration.placeholder.AncapPlaceholderableString;
import ru.ancap.misc.placeholder.PlaceholderSource;
import ru.ancap.misc.placeholder.exception.NoSuchPlaceholderException;

import java.util.ArrayList;
import java.util.List;

public class AncapPlaceholderedConfigurationSection extends AncapConfigurationSection implements ConfigurationSection {

    private PlaceholderSource placeholderSource;

    public AncapPlaceholderedConfigurationSection(ConfigurationSection configurationSection, PlaceholderSource source) {
        super(configurationSection);
        this.placeholderSource = source;
    }

    public AncapPlaceholderedConfigurationSection(AncapPlaceholderedConfigurationSection section) {
        this(section.getConfigurationSection(), section.getPlaceholderSource());
    }

    protected PlaceholderSource getPlaceholderSource() {
        return this.placeholderSource;
    }

    @Override
    public String getString(@NotNull String path) {
        try {
            return this.getPlaceholder(super.getString(path));
        } catch (NoSuchPlaceholderException e) {
            throw this.getBadConfigurationException(path, e);
        }
    }

    @Override
    public @NotNull List<String> getStringList(@NotNull String path) {
        List<String> list = new ArrayList<>();
        try {
            for (String string : super.getStringList(path)) {
                list.add(this.getPlaceholder(string));
            }
        } catch (NoSuchPlaceholderException e) {
            throw this.getBadConfigurationException(path, e);
        }
        return list;
    }

    private String getPlaceholder(String placeholder) throws NoSuchPlaceholderException {
        return new AncapPlaceholderableString(placeholder, this.placeholderSource).getPlaceholdered();
    }

    private BadConfigurationException getBadConfigurationException(String path, NoSuchPlaceholderException e) {
        return new BadConfigurationException(path, "No such placeholder: "+e.getPlaceholder().getName());
    }
}
