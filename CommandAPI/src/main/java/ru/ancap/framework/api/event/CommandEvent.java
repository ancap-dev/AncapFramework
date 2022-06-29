package ru.ancap.framework.api.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.command.CommandSender;

@Getter
@AllArgsConstructor
public abstract class CommandEvent extends OperableEvent {

    private final CommandSender sender;

}
