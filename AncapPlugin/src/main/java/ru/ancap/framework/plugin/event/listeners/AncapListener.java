package ru.ancap.framework.plugin.event.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

public abstract class AncapListener implements Listener {

    private final PluginManager pluginManager;

    public AncapListener() {
        this.pluginManager = Bukkit.getPluginManager();
    }

    public AncapListener(PluginManager pluginManager) {
        this.pluginManager = pluginManager;
    }

    protected PluginManager getPluginManager() {
        return this.pluginManager;
    }

    protected void throwEvent(Event event) {
        this.pluginManager.callEvent(event);
    }

}
