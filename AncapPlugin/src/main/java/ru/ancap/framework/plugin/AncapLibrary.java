package ru.ancap.framework.plugin;

import javafx.util.Pair;
import org.bukkit.event.Listener;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.ancap.framework.api.LAPI;
import ru.ancap.framework.api.command.commands.command.executor.CommandOperator;
import ru.ancap.framework.api.locale.MapLocales;
import ru.ancap.framework.api.plugin.plugins.AncapPlugin;
import ru.ancap.framework.plugin.command.library.commands.LanguageCommandExecutor;
import ru.ancap.framework.plugin.configuration.AncapPluginConfig;
import ru.ancap.framework.plugin.event.listeners.api.addition.BlockClickListener;
import ru.ancap.framework.plugin.event.listeners.api.addition.VillagerHealListener;
import ru.ancap.framework.plugin.event.listeners.api.wrapper.ExplodeListener;
import ru.ancap.framework.plugin.event.listeners.api.wrapper.ProtectListener;
import ru.ancap.framework.plugin.event.listeners.api.wrapper.SelfDestructListener;
import ru.ancap.framework.plugin.event.timer.AncapTimerEventListener;
import ru.ancap.framework.plugin.heartbeat.AncapHeartbeat;
import ru.ancap.framework.plugin.language.locale.loader.AncapPluginLocaleLoader;
import ru.ancap.framework.plugin.language.module.LanguageBase;
import ru.ancap.framework.plugin.language.module.LanguagesOperator;
import ru.ancap.framework.plugin.language.module.model.SpeakerModel;
import ru.ancap.framework.plugin.language.module.repository.HibernateSpeakerModelRepository;

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

    private final List<Pair<String, CommandOperator>> commands = List.of(
            new Pair<>("language", new LanguageCommandExecutor())
    );

    private SessionFactory hibernateFactory;

    @Override
    public void onEnable() {
        super.onEnable();
        this.startHeartbeat();
        this.loadConfig();
        this.loadDatabase();
        this.loadLAPI();
        this.loadLocales();
    }

    private void loadDatabase() {
        this.getResourceSource().getResource("langs.sqlite");
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(SpeakerModel.class);
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        this.hibernateFactory = configuration.buildSessionFactory(builder.build());
    }

    private void loadConfig() {
        new AncapPluginConfig(
                this.getConfig()
        ).load();
    }

    private void loadLAPI() {
        LAPI.setup(
                new MapLocales(AncapPluginConfig.loaded().defaultLanguage()),
                new LanguageBase(
                        new LanguagesOperator(
                                new HibernateSpeakerModelRepository(this.hibernateFactory)
                        ),
                        AncapPluginConfig.loaded().defaultLanguage()
                )
        );
    }


    private void loadLocales() {
        new Thread(
                () -> new AncapPluginLocaleLoader(
                        new File(
                                this.getDataFolder(), "locales")
                ).load()
        ).start();
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
