package ru.ancap.framework.api.command.commands.command.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.ancap.framework.api.command.commands.command.dispatched.LeveledCommand;
import ru.ancap.framework.api.command.commands.command.executor.conversation.CommandLineSpeaker;

@AllArgsConstructor
@Getter
public class CommandWrite {

    private final CommandLineSpeaker speaker;
    private final LeveledCommand written;


}
