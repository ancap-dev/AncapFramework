package ru.ancap.framework.plugin.api.incubator.commands.exceptions;

public abstract class HandleableException extends Exception {

    abstract void handle();
}
