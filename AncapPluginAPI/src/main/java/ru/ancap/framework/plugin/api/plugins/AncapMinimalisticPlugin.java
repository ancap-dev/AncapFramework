package ru.ancap.framework.plugin.api.plugins;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import ru.ancap.framework.plugin.api.incubator.commands.CommandExecutor;
import ru.ancap.framework.plugin.api.plugins.exception.AlreadyLoadedException;
import ru.ancap.framework.plugin.coreplugin.Ancap;

import java.util.ArrayList;
import java.util.List;

public abstract class AncapMinimalisticPlugin extends JavaPlugin {

    private static Ancap ANCAP;

    @Override
    public void onEnable() {
        super.onEnable();
        this.registerEventsListeners();
        this.registerCommands();
    }

    protected void registerEventsListeners() {
        for (Listener listener : this.getListeners()) {
            this.registerEventsListener(listener);
        }
    }

    protected void registerEventsListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    protected void registerCommands() {
        for (CommandExecutor executor : this.getExecutors()) {
            this.registerCommand(executor);
        }
    }

    private void registerCommand(CommandExecutor executor) {
        executor.register();
    }

    protected Ancap getAncap() {
        return ANCAP;
    }

    protected List<Listener> getListeners() {
        return new ArrayList<>();
    }

    protected List<CommandExecutor> getExecutors() {
        return new ArrayList<>();
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

    protected static synchronized void loadAncap(Ancap ancap) {
        AncapMinimalisticPlugin.validateFirstLoad();
        ANCAP = ancap;
    }

    private static synchronized void validateFirstLoad() {
        if (ANCAP != null) {
            throw new AlreadyLoadedException();
        }
    }
}
