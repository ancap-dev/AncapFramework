package ru.ancap.framework.plugin.api.configuration.exception;

public class InvalidConfigurationPacketException extends InvalidConfigurationException {

    public InvalidConfigurationPacketException(String path, String reason) {
        super(path, reason);
    }
}
