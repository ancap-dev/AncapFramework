package ru.ancap.framework.api.plugin.plugins;

import javafx.util.Pair;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import ru.ancap.framework.api.command.commands.command.executor.CommandExecutor;
import ru.ancap.framework.api.command.executor.WrapExecutor;
import ru.ancap.framework.api.plugin.plugins.exception.CommandRegisterException;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.util.ArrayList;
import java.util.List;

public abstract class AncapMinimalisticPlugin extends JavaPlugin {

    @OverridingMethodsMustInvokeSuper
    @Override
    public void onEnable() {
        super.onEnable();
        this.registerAutoRegisteredListeners();
        this.registerAutoRegisteredCommandExecutors();
    }

    private void registerAutoRegisteredCommandExecutors() {
        for (Pair<String, CommandExecutor> command : this.commands()) {
            this.registerCommand(command.getKey(), command.getValue());
        }
    }

    private void registerAutoRegisteredListeners() {
        for (Listener listener : this.listeners()) {
            this.registerEventsListener(listener);
        }
    }

    protected void registerEventsListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    protected void registerCommand(String commandName, org.bukkit.command.CommandExecutor executor) {
        PluginCommand command = this.getCommand(commandName);
        if (command == null) {
            throw new CommandRegisterException("Command must be declared in plugin.yml!");
        }
        command.setExecutor(executor);
    }

    protected void registerCommand(String commandName, CommandExecutor executor) {
        this.registerCommand(commandName, new WrapExecutor(executor));
    }

    protected ResourceSource getResourceSource() {
        return this.newResourceSource(true);
    }

    protected ResourceSource getSoftResourceSource() {
        return this.newResourceSource(false);
    }

    private ResourceSource newResourceSource(boolean saveFiles) {
        return new AncapPluginResourceSource(this, saveFiles);
    }

    protected List<Listener> listeners() {
        return new ArrayList<>();
    }
    protected List<Pair<String, CommandExecutor>> commands() {
        return new ArrayList<>();
    }
}
