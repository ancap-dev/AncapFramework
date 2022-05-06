package ru.ancap.framework.plugin.api.incubator.commands;

import org.bukkit.command.ConsoleCommandSender;

import java.util.List;

public class AncapConsoleCommand {

    private ConsoleCommandSender sender;
    private List<String> args;

    public AncapConsoleCommand(ConsoleCommandSender sender, List<String> args) {
        this.sender = sender;
        this.args = args;
    }

    public AncapConsoleCommand(AncapConsoleCommand command) {
        this(command.getSender(), command.getArgs());
    }

    public ConsoleCommandSender getSender() {
        return this.sender;
    }

    public List<String> getArgs() {
        return this.args;
    }

}
