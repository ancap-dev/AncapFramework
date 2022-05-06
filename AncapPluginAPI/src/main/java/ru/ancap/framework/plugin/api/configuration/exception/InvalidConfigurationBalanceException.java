package ru.ancap.framework.plugin.api.configuration.exception;

public class InvalidConfigurationBalanceException extends InvalidConfigurationException {

    public InvalidConfigurationBalanceException(String path, String reason) {
        super(path, reason);
    }
}
