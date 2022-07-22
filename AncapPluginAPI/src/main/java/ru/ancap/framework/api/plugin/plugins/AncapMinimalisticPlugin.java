package ru.ancap.framework.api.plugin.plugins;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import ru.ancap.framework.api.loader.YamlLocaleLoader;
import ru.ancap.framework.api.plugin.plugins.config.StreamConfig;

import javax.annotation.OverridingMethodsMustInvokeSuper;
import java.util.ArrayList;
import java.util.List;

public abstract class AncapMinimalisticPlugin extends JavaPlugin {

    private static Ancap ancap;

    @OverridingMethodsMustInvokeSuper
    @Override
    public void onEnable() {
        super.onEnable();
        this.createPluginFolder();
        this.saveDefaultConfig();
    }

    protected void onCoreLoad() {
        // to override it in core plugin
    }

    protected Ancap getAncap() {
        return ancap;
    }

    protected void unloadAncap() {
        if (AncapMinimalisticPlugin.ancap == null) {
            throw new IllegalStateException("Ancap isn't loaded!");
        }
        AncapMinimalisticPlugin.ancap = null;
    }

    protected void loadAncap(Ancap ancap) {
        if (AncapMinimalisticPlugin.ancap != null) {
            throw new IllegalStateException("Ancap already loaded!");
        }
        AncapMinimalisticPlugin.ancap = ancap;
    }

    private void createPluginFolder() {
        if (!this.getDataFolder().exists()) {
            this.getDataFolder().mkdirs();
        }
    }

    protected void loadLocale(String fileName) {
        new YamlLocaleLoader(
                new StreamConfig(
                        this.getResource(fileName)
                )
        ).run();
    }

    protected void registerAutoRegisteredListeners() {
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

    protected ResourceSource getResourceSource() {
        return ancap.newResourceSource(this, true);
    }

    protected ResourceSource getSoftResourceSource() {
        return ancap.newResourceSource(this, false);
    }

    protected List<Listener> listeners() {
        return new ArrayList<>();
    }
}
