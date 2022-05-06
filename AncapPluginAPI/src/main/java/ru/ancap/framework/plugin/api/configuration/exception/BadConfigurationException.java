package ru.ancap.framework.plugin.api.configuration.exception;

public class BadConfigurationException extends RuntimeException {

    private String path;
    private String reason;

    public BadConfigurationException(String path, String reason) {
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
