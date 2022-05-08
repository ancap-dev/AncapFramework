package ru.ancap.framework.plugin.api.configuration.extended.entensions.exceptions;

import ru.ancap.framework.plugin.api.configuration.exception.InvalidConfigurationException;

public class InvalidConfigurationMapException extends InvalidConfigurationException {

    public InvalidConfigurationMapException(String path, String reason) {
        super(path, reason);
    }
}
