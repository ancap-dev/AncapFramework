package ru.ancap.framework.plugin.api.configuration.extended.entensions.exceptions;

import ru.ancap.framework.plugin.api.configuration.exception.InvalidConfigurationException;

public class InvalidConfigurationSendableException extends InvalidConfigurationException {

    public InvalidConfigurationSendableException(String path, String reason) {
        super(path, reason);
    }
}
