package ru.ancap.framework.plugin;

import com.github.retrooper.packetevents.PacketEvents;
import com.github.retrooper.packetevents.event.PacketListenerPriority;
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder;
import javafx.util.Pair;
import org.bukkit.event.Listener;
import ru.ancap.framework.api.LAPI;
import ru.ancap.framework.api.command.commands.command.executor.CommandOperator;
import ru.ancap.framework.api.database.ConnectionProvider;
import ru.ancap.framework.api.locale.MapLocales;
import ru.ancap.framework.api.plugin.plugins.AncapPlugin;
import ru.ancap.framework.api.reader.ConfigDatabaseLoader;
import ru.ancap.framework.plugin.command.api.implementation.AsyncCommandCenter;
import ru.ancap.framework.plugin.command.api.implementation.catcher.CommandCatcher;
import ru.ancap.framework.plugin.command.library.commands.LanguageCommandExecutor;
import ru.ancap.framework.plugin.configuration.AncapPluginConfig;
import ru.ancap.framework.plugin.event.listeners.api.addition.BlockClickListener;
import ru.ancap.framework.plugin.event.listeners.api.addition.VillagerHealListener;
import ru.ancap.framework.plugin.event.listeners.api.wrapper.ExplodeListener;
import ru.ancap.framework.plugin.event.listeners.api.wrapper.ProtectListener;
import ru.ancap.framework.plugin.event.listeners.api.wrapper.SelfDestructListener;
import ru.ancap.framework.plugin.event.listeners.language.LanguageChangeListener;
import ru.ancap.framework.plugin.event.timer.AncapTimerEventListener;
import ru.ancap.framework.plugin.heartbeat.AncapHeartbeat;
import ru.ancap.framework.plugin.language.listener.LAPIJoinListener;
import ru.ancap.framework.plugin.language.module.LanguageBase;
import ru.ancap.framework.plugin.language.module.LanguagesOperator;
import ru.ancap.framework.plugin.language.module.repository.SQLSpeakerModelRepository;
import ru.ancap.framework.plugin.language.module.repository.SpeakerModelRepository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public final class AncapLibrary extends AncapPlugin {

    private final List<Listener> listeners = List.of(
            new ProtectListener(),
            new SelfDestructListener(),
            new ExplodeListener(),
            new VillagerHealListener(),
            new BlockClickListener(),
            new AncapTimerEventListener(),
            new LanguageChangeListener()
    );

    private final List<Pair<String, CommandOperator>> commands = List.of(
            new Pair<>("language", new LanguageCommandExecutor())
    );

    private AsyncCommandCenter asyncCommandCenter;
    private ConnectionProvider connectionProvider;

    @Override
    public void onLoad() {
        this.loadPacketEventsApi();
    }

    @Override
    public void onCoreLoad() {
        this.loadConfig();
        this.loadAncap();
        this.registerCommandCenter();
        this.startHeartbeat();
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.loadListeners();
        this.initPacketEventsApi();
        this.loadDatabase();
        this.loadLAPI();
        this.loadLocales();
    }

    private void initPacketEventsApi() {
        PacketEvents.getAPI().init();
    }

    private void loadPacketEventsApi() {
        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(this));
        PacketEvents.getAPI().getSettings().readOnlyListeners(false)
                .checkForUpdates(true)
                .bStats(true);
        PacketEvents.getAPI().load();
    }

    private void loadListeners() {
        CommandCatcher catcher = new CommandCatcher(asyncCommandCenter, asyncCommandCenter);
        this.registerEventsListener(
                catcher
        );
        PacketEvents.getAPI().getEventManager().registerListener(
                catcher,
                PacketListenerPriority.LOW
        );
    }

    private void registerCommandCenter() {
        this.asyncCommandCenter = new AsyncCommandCenter();
        this.registerCommandCenter(
                asyncCommandCenter
        );
    }

    private void loadAncap() {
        this.loadAncap(
                new AncapPluginAncap(
                        new ConfigDriverSettingsReader(
                                AncapPluginConfig.loaded().getDatabaseDriverDataSection()
                        ).get()
                )
        );
    }

    private void loadDatabase() {
        this.connectionProvider = new ConfigDatabaseLoader(
                this,
                this.getAncap().getGlobalDatabaseProperties(),
                AncapPluginConfig.loaded().getDatabaseConnectionSection(),
                (context) -> {
                    String sql = """
                                 CREATE TABLE Languages (
                                 name VARCHAR(32) PRIMARY KEY,
                                 language_code VARCHAR(8) 
                                 );
                                 """;
                    try (PreparedStatement statement = context.connection().prepareStatement(sql)) {
                        statement.execute();
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                }
        ).load().provider();
    }

    private void loadConfig() {
        new AncapPluginConfig(
                this.getConfig()
        ).load();
    }

    private void loadLAPI() {
        SpeakerModelRepository repository = new SQLSpeakerModelRepository(this.connectionProvider);
        this.registerEventsListener(
                new LAPIJoinListener(repository)
        );
        LAPI.setup(
                new MapLocales(
                        AncapPluginConfig.loaded().defaultLanguage()
                ),
                new LanguageBase(
                        new LanguagesOperator(repository),
                        AncapPluginConfig.loaded().defaultLanguage()
                )
        );
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
    public List<Pair<String, CommandOperator>> commands() {
        return this.commands;
    }
}
