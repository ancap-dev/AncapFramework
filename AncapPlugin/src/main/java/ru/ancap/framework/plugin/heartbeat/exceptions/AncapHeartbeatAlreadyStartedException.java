package ru.ancap.framework.plugin.heartbeat.exceptions;

public class AncapHeartbeatAlreadyStartedException extends RuntimeException {

    public AncapHeartbeatAlreadyStartedException(String message) {
        super(message);
    }
}
