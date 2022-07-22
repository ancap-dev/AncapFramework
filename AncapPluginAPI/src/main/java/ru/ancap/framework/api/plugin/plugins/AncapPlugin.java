package ru.ancap.framework.api.plugin.plugins;

import javafx.util.Pair;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import ru.ancap.framework.api.command.commands.command.executor.CommandOperator;
import ru.ancap.framework.api.plugin.plugins.commands.CommandCenter;
import ru.ancap.framework.api.plugin.plugins.config.StreamConfig;
import ru.ancap.framework.api.plugin.plugins.info.AncapPluginSettings;
import ru.ancap.framework.api.plugin.plugins.language.locale.loader.LocaleLoader;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AncapPlugin extends AncapMinimalisticPlugin {

    public static final String MESSAGE_DOMAIN = "ru.ancap.framework.messages.";

    private static final Map<String, AncapPlugin> plugins = new HashMap<>();
    private static CommandCenter commandCenter;

    private Metrics metrics;
    private AncapPluginSettings settings;


    protected Metrics getMetrics() {
        return this.metrics;
    }

    @OverridingMethodsMustInvokeSuper
    @Override
    public void onEnable() {
        super.onEnable();
        this.registerAutoRegisteredListeners();
        this.onCoreLoad();
        this.loadPluginSettings();
        this.registerMetrics();
        this.initializeInCommandCenter();
        this.registerAutoRegisteredCommandExecutors();
        this.register();
    }

    private void initializeInCommandCenter() {
        commandCenter.initialize(this);
    }

    protected void registerCommandCenter(CommandCenter commandCenter) {
        if (AncapPlugin.commandCenter != null) {
            throw new IllegalStateException("Command center is already registered!");
        }
        AncapPlugin.commandCenter = commandCenter;
    }

    protected void registerExecutor(String commandName, CommandOperator executor) {
        Bukkit.broadcastMessage(
                "Registering command: " +commandName+" with executor "+executor.getClass().getName()
        );
        commandCenter.setExecutor(commandName, executor);
    }

    private void registerAutoRegisteredCommandExecutors() {
        for (Pair<String, CommandOperator> command : this.commands()) {
            this.registerExecutor(
                    command.getKey(),
                    command.getValue()
            );
        }
    }

    protected void loadLocales() {
        new LocaleLoader(
                this.getLogger(),
                this.getResourceSource()
        ).run();
    }

    protected Iterable<AncapPlugin> ancapPlugins() {
        return plugins.values();
    }

    private void register() {
        plugins.put(this.getName(), this);
    }

    private void unregister() {
        plugins.remove(this.getName());
    }

    @Override
    public void onDisable() {
        this.unregister();
    }

    public AncapPluginSettings getSettings() {
        return this.settings;
    }

    private void loadPluginSettings() {
        this.settings = new AncapPluginSettings(
                new StreamConfig(
                        this.getSoftResourceSource().getResource("ancapplugin.yml")
                )
        );
    }

    protected void registerMetrics() {
        this.metrics = new Metrics(this, this.getPluginId());
        // metrics.enable();
    }

    protected int getPluginId() {
        return this.getSettings().getPluginId();
    }

    protected List<Pair<String, CommandOperator>> commands() {
        return new ArrayList<>();
    }

}
