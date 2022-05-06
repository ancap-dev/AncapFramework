package ru.ancap.framework.plugin.api.incubator.commands;

import org.bukkit.entity.Player;

import java.util.List;

public class AncapCommand {

    private Player sender;
    private List<String> args;

    public AncapCommand(Player sender, List<String> args) {
        this.sender = sender;
        this.args = args;
    }

    public AncapCommand(AncapCommand command) {
        this(command.getSender(), command.getArgs());
    }

    public Player getSender() {
        return this.sender;
    }

    public List<String> getArgs() {
        return this.args;
    }

}
