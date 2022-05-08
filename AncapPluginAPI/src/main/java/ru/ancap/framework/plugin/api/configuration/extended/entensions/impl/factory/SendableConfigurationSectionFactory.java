package ru.ancap.framework.plugin.api.configuration.extended.entensions.impl.factory;

import org.bukkit.configuration.ConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.api.SendableConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.exceptions.InvalidConfigurationSendableException;

public interface SendableConfigurationSectionFactory {

    SendableConfigurationSection getFrom(ConfigurationSection section) throws InvalidConfigurationSendableException;

}
