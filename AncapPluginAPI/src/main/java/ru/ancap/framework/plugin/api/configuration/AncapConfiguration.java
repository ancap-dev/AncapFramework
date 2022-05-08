package ru.ancap.framework.plugin.api.configuration;

import org.bukkit.configuration.Configuration;
import ru.ancap.framework.plugin.api.configuration.extended.AncapExtendedConfiguration;
import ru.ancap.framework.plugin.api.configuration.extended.AncapExtendedConfigurationSection;
import ru.ancap.misc.placeholder.Placeholder;
import ru.ancap.misc.placeholder.PlaceholderSource;
import ru.ancap.misc.placeholder.PlaceholderSourceBuilder;
import ru.ancap.misc.placeholder.PlaceholderSourceBuilderImpl;
import ru.ancap.misc.placeholder.exception.NoSuchPlaceholderException;

public abstract class AncapConfiguration extends AncapExtendedConfiguration implements PlaceholderSource {

    public AncapConfiguration(AncapExtendedConfigurationSection section, Configuration configuration) {
        super(section, configuration);
    }

    public AncapConfiguration(AncapExtendedConfiguration configuration) {
        super(configuration);
    }

    protected PlaceholderSourceBuilder getNewSourceBuilder() {
        return new PlaceholderSourceBuilderImpl()
                .addSource(this);
    }

    @Override
    public String getValue(Placeholder placeholder) throws NoSuchPlaceholderException {
        if (!this.isSet(placeholder.getName())) {
            throw new NoSuchPlaceholderException(placeholder);
        }
        return this.getString(placeholder.getName());
    }
}
