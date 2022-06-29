package ru.ancap.framework.api.plugin.plugins;

import org.bstats.bukkit.Metrics;
import ru.ancap.framework.api.plugin.plugins.config.StreamConfig;
import ru.ancap.framework.api.plugin.plugins.info.AncapPluginSettings;

import javax.annotation.OverridingMethodsMustInvokeSuper;

public abstract class AncapPlugin extends AncapMinimalisticPlugin {

    public static final String MESSAGE_DOMAIN = "ru.ancap.framework.messages.";;

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
    }

    /**
     * It's bad practice to use onDisable logic, because it might be not called in some
     * cases like server crash, so I force-disabled it in AncapFramework.
     */

    @Override
    public final void onDisable() {
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
