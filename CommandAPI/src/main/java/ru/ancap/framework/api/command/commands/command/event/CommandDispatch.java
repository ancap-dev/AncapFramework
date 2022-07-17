package ru.ancap.framework.api.command.commands.command.event;

import org.bukkit.command.CommandSender;
import ru.ancap.framework.api.command.commands.command.dispatched.TextCommand;

public record CommandDispatch(CommandSender sender, TextCommand dispatched) {

}
