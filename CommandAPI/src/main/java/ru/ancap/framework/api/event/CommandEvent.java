package ru.ancap.framework.api.event;

import lombok.Getter;
import org.bukkit.command.CommandSender;

@Getter
public abstract class CommandEvent extends OperableEvent {

    private final CommandSender sender;

    public CommandEvent(CommandSender sender) {
        super(true);
        this.sender = sender;
    }
}
