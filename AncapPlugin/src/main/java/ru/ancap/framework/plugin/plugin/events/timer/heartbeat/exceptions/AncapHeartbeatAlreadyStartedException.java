package ru.ancap.framework.plugin.plugin.events.timer.heartbeat.exceptions;

public class AncapHeartbeatAlreadyStartedException extends RuntimeException {

    public AncapHeartbeatAlreadyStartedException(String message) {
        super(message);
    }
}
