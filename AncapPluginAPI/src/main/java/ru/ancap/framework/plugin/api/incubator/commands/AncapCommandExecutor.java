package ru.ancap.framework.plugin.api.incubator.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import ru.ancap.framework.plugin.api.incubator.commands.exceptions.UnknownCommandSenderException;

import java.util.List;

public abstract class AncapCommandExecutor implements CommandExecutor {

    private String operatedCommandName;

    public AncapCommandExecutor(String operatedCommandName) {
        this.operatedCommandName = operatedCommandName;
    }

    protected String getOperatedCommandName() {
        return this.operatedCommandName;
    }

    private PluginCommand getOperatedCommand() {
        return Bukkit.getPluginCommand(this.getOperatedCommandName());
    }

    @Override
    public void register() {
        AncapCommandExecutor executor = this;
        this.getOperatedCommand().setExecutor((sender, command, label, args) -> {
            if (sender instanceof ConsoleCommandSender) {
                executor.onConsoleCommand(new AncapConsoleCommand((ConsoleCommandSender) sender, List.of(args)));
            } else if (sender instanceof Player) {
                executor.onPlayerCommand(new AncapCommand((Player) sender, List.of(args)));
            } else {
                throw new UnknownCommandSenderException();
            }
            return true;
        });
    }

}
