package ru.ancap.framework.api.plugin.plugins;

import org.bstats.bukkit.Metrics;
import ru.ancap.framework.api.plugin.plugins.config.StreamConfig;
import ru.ancap.framework.api.plugin.plugins.info.AncapPluginSettings;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.util.*;

public abstract class AncapPlugin extends AncapMinimalisticPlugin {

    public static final String MESSAGE_DOMAIN = "ru.ancap.framework.messages.";

    private static final Map<String, AncapPlugin> plugins = new HashMap<>();

    private Metrics metrics;
    private AncapPluginSettings settings;

    protected Metrics getMetrics() {
        return this.metrics;
    }

    @OverridingMethodsMustInvokeSuper
    @Override
    public void onEnable() {
        super.onEnable();
        this.loadPluginSettings();
        this.registerMetrics();
        this.register();
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
                new StreamConfig(this.getSoftResourceSource().getResource("ancapplugin.yml"))
        );
    }

    protected void registerMetrics() {
        this.metrics = new Metrics(this, this.getPluginId());
        // metrics.enable();
    }

    protected int getPluginId() {
        return this.getSettings().getPluginId();
    }

}
