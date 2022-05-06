package ru.ancap.framework.plugin.api.incubator.commands;

import org.bukkit.command.CommandSender;
import ru.ancap.misc.preparable.Preparable;

import java.util.List;

public abstract class PreCommand implements Preparable {

    private List<String> args;
    private CommandSender sender;

    public PreCommand(List<String> args, CommandSender sender) {
        this.args = args;
        this.sender = sender;
    }

    protected List<String> getArgs() {
        return this.args;
    }

    protected CommandSender getSender() {
        return this.sender;
    }

    @Override
    public abstract Command getPrepared();
}
