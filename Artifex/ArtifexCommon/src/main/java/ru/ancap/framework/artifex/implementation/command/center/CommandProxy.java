package ru.ancap.framework.artifex.implementation.command.center;

import lombok.NonNull;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import ru.ancap.framework.artifex.implementation.command.event.ProxiedCommandEvent;

public class CommandProxy implements CommandExecutor {

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, @NonNull String[] arguments) {
        String line = command.getName() + " " + String.join(" ", arguments);
        Bukkit.getPluginManager().callEvent(new ProxiedCommandEvent(sender, line));
        return true;
    }
    
}
