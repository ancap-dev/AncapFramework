package ru.ancap.framework.api.command.commands.command.dispatched;

public interface LeveledCommand {

    boolean isRaw();
    String nextArgument();
    LeveledCommand withoutArgument();

}
