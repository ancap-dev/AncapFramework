package ru.ancap.framework.api.plugin.plugins.commands;

import ru.ancap.framework.api.command.commands.command.executor.CommandOperator;
import ru.ancap.framework.api.plugin.plugins.AncapPlugin;
import ru.ancap.framework.api.plugin.plugins.exception.CommandNotRegisteredException;

/**
 * Alternative command center.
 * Advantages over the loader from Bukkit depends on realization. By default, check AsyncPacketCommandCenter in AncapPlugin.
 */
public interface CommandCenter {

    void initialize(AncapPlugin plugin);
    void setExecutor(String commandName, CommandOperator executor) throws CommandNotRegisteredException;

}
