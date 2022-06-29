package ru.ancap.framework.plugin;

import javafx.util.Pair;
import org.bukkit.event.Listener;
import ru.ancap.framework.api.command.commands.command.executor.CommandExecutor;
import ru.ancap.framework.api.plugin.plugins.AncapPlugin;
import ru.ancap.framework.plugin.command.LanguageCommandExecutor;
import ru.ancap.framework.plugin.event.listeners.api.addition.BlockClickListener;
import ru.ancap.framework.plugin.event.listeners.api.addition.VillagerHealListener;
import ru.ancap.framework.plugin.event.listeners.api.wrapper.ExplodeListener;
import ru.ancap.framework.plugin.event.listeners.api.wrapper.ProtectListener;
import ru.ancap.framework.plugin.event.listeners.api.wrapper.SelfDestructListener;
import ru.ancap.framework.plugin.event.timer.AncapTimerEventListener;
import ru.ancap.framework.plugin.heartbeat.AncapHeartbeat;
import ru.ancap.framework.plugin.language.locale.loader.AncapPluginLocaleLoader;

import java.io.File;
import java.util.List;

public final class AncapLibrary extends AncapPlugin {

    private final List<Listener> listeners = List.of(
            new ProtectListener(),
            new SelfDestructListener(),
            new ExplodeListener(),
            new VillagerHealListener(),
            new BlockClickListener(),
            new AncapTimerEventListener()
    );

    private final List<Pair<String, CommandExecutor>> commands = List.of(
            new Pair<>("language", new LanguageCommandExecutor())
    );

    @Override
    public void onEnable() {
        super.onEnable();
        this.startHeartbeat();
        this.loadLocales();
    }

    private void loadLocales() {
        new Thread(() -> new AncapPluginLocaleLoader(new File(this.getDataFolder(), "locales")).load()).start();
    }

    private void startHeartbeat() {
        AncapHeartbeat heartbeat = new AncapHeartbeat(this);
        heartbeat.start();
    }

    @Override
    public List<Listener> listeners() {
        return this.listeners;
    }

    @Override
    public List<Pair<String, CommandExecutor>> commands() {
        return this.commands;
    }
}
