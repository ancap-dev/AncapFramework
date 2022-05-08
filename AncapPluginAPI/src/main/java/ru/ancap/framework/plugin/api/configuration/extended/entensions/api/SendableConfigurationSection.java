package ru.ancap.framework.plugin.api.configuration.extended.entensions.api;

import ru.ancap.framework.plugin.api.configuration.extended.entensions.exceptions.InvalidConfigurationSendableException;
import ru.ancap.framework.plugin.api.packet.api.packet.Sendable;

public interface SendableConfigurationSection {

    Sendable getSendable() throws InvalidConfigurationSendableException;

}
