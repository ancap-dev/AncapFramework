package ru.ancap.framework.plugin.api.configuration;

import org.bukkit.configuration.Configuration;
import ru.ancap.framework.plugin.api.configuration.extended.AncapExtendedConfiguration;
import ru.ancap.framework.plugin.api.configuration.extended.AncapExtendedConfigurationSection;

public class AncapConfigurationImpl extends AncapConfiguration {
    public AncapConfigurationImpl(AncapExtendedConfigurationSection section, Configuration configuration) {
        super(section, configuration);
    }

    public AncapConfigurationImpl(AncapExtendedConfiguration configuration) {
        super(configuration);
    }
}
