package ru.ancap.framework.plugin.api.configuration.exception;

public class InvalidConfigurationException extends Exception {

    private String path;
    private String reason;

    public InvalidConfigurationException(String path, String reason) {
        this.path = path;
        this.reason = reason;
    }

    public String getPath() {
        return this.path;
    }

    public String getReason() {
        return this.reason;
    }
}
