package ru.ancap.framework.api.plugin.plugins;

import javafx.util.Pair;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import ru.ancap.framework.api.command.commands.command.executor.CommandOperator;
import ru.ancap.framework.api.loader.YamlLocaleLoader;
import ru.ancap.framework.api.plugin.plugins.commands.CommandCenter;
import ru.ancap.framework.api.plugin.plugins.config.StreamConfig;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.util.ArrayList;
import java.util.List;

public abstract class AncapMinimalisticPlugin extends JavaPlugin {

    private static CommandCenter commandCenter;

    @OverridingMethodsMustInvokeSuper
    @Override
    public void onEnable() {
        super.onEnable();
        this.registerAutoRegisteredListeners();
        this.registerAutoRegisteredCommandExecutors();
    }

    protected void loadLocale(String fileName) {
        new YamlLocaleLoader(
                new StreamConfig(
                        this.getResource(fileName)
                )
        ).load();
    }

    private void registerAutoRegisteredCommandExecutors() {
        for (Pair<String, CommandOperator> command : this.commands()) {
            this.registerExecutor(
                    command.getKey(),
                    command.getValue()
            );
        }
    }

    private void registerAutoRegisteredListeners() {
        for (Listener listener : this.listeners()) {
            this.registerEventsListener(listener);
        }
    }

    protected void registerEventsListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(
                listener,
                this
        );
    }

    protected void registerCommandCenter(CommandCenter commandCenter) {
        if (AncapMinimalisticPlugin.commandCenter != null) {
            throw new IllegalStateException("Command center is already registered!");
        }
        AncapMinimalisticPlugin.commandCenter = commandCenter;
    }

    protected void registerExecutor(String commandName, CommandOperator executor) {
        commandCenter.setExecutor(commandName, executor);
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
    protected List<Pair<String, CommandOperator>> commands() {
        return new ArrayList<>();
    }
}
