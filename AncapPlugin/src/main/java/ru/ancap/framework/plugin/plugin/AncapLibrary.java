package ru.ancap.framework.plugin.plugin;

import org.bukkit.event.Listener;
import ru.ancap.framework.plugin.api.configuration.PreAncapConfiguration;
import ru.ancap.framework.plugin.api.incubator.commands.CommandExecutor;
import ru.ancap.framework.plugin.coreplugin.Ancap;
import ru.ancap.framework.plugin.coreplugin.AncapCore;
import ru.ancap.framework.plugin.coreplugin.CoreMultiLanguageConfiguration;
import ru.ancap.framework.plugin.coreplugin.CorePlugin;
import ru.ancap.framework.plugin.plugin.commands.test.LibraryTestExecutor;
import ru.ancap.framework.plugin.plugin.configuration.AncapLibraryConfiguration;
import ru.ancap.framework.plugin.plugin.configuration.AncapLibraryMultiLanguageMessageConfiguration;
import ru.ancap.framework.plugin.plugin.events.listeners.addition.BlockClickListener;
import ru.ancap.framework.plugin.plugin.events.listeners.wrapper.ExplodeListener;
import ru.ancap.framework.plugin.plugin.events.listeners.wrapper.ProtectListener;
import ru.ancap.framework.plugin.plugin.events.listeners.wrapper.SelfDestructListener;
import ru.ancap.framework.plugin.plugin.events.listeners.wrapper.VillagerHealListener;
import ru.ancap.framework.plugin.plugin.events.timer.AncapTimerEventListener;
import ru.ancap.framework.plugin.plugin.events.timer.heartbeat.AncapHeartbeat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class AncapLibrary extends CorePlugin {

    private AncapLibraryMultiLanguageMessageConfiguration configuration;

    private final List<CommandExecutor> executors = new ArrayList<>(Arrays.asList(
            new LibraryTestExecutor("run-framework-test", this)
    ));

    private final List<Listener> listeners = new ArrayList<>(Arrays.asList(
            new ProtectListener(),
            new SelfDestructListener(),
            new ExplodeListener(),
            new VillagerHealListener(),
            new BlockClickListener(),
            new AncapTimerEventListener()
    ));

    @Override
    public void onEnable() {
        super.onEnable();
        this.linkageConfiguration();
        this.startHeartbeat();
        this.enableAncap();
    }

    private void enableAncap() {
        Ancap ancap = new AncapCore(this);
        this.loadAncap(ancap);
    }

    private void linkageConfiguration() {
        this.configuration = new AncapLibraryMultiLanguageMessageConfiguration(super.getMultiLanguageConfiguration());
    }

    private void startHeartbeat() {
        this.getAncap().getCorePlugin().getCoreMessageConfiguration();
        AncapHeartbeat heartbeat = new AncapHeartbeat(this);
        heartbeat.start();
    }

    public AncapLibraryMultiLanguageMessageConfiguration getMessageConfiguration() {
        return this.configuration;
    }

    public AncapLibraryConfiguration getConfiguration() {
        return new AncapLibraryConfiguration(
                new PreAncapConfiguration(this.getResourceSource(), "config")
                        .getPrepared()
        );
    }

    @Override
    protected List<Listener> getListeners() {
        return this.listeners;
    }

    @Override
    protected List<CommandExecutor> getExecutors() {
        return this.executors;
    }

    @Override
    public CoreMultiLanguageConfiguration getCoreMessageConfiguration() {
        return this.getMessageConfiguration();
    }
}
