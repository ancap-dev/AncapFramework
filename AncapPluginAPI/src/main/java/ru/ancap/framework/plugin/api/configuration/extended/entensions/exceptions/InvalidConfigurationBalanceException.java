package ru.ancap.framework.plugin.api.configuration.extended.entensions.exceptions;

import ru.ancap.framework.plugin.api.configuration.exception.InvalidConfigurationException;

public class InvalidConfigurationBalanceException extends InvalidConfigurationException {

    public InvalidConfigurationBalanceException(String path, String reason) {
        super(path, reason);
    }
}
