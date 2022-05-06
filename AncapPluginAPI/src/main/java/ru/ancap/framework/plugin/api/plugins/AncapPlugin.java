package ru.ancap.framework.plugin.api.plugins;

import org.bstats.bukkit.Metrics;
import org.bukkit.event.Listener;
import ru.ancap.framework.plugin.api.configuration.PreAncapConfiguration;
import ru.ancap.framework.plugin.api.configuration.builder.AncapMultiLanguageConfigurationBuilderImpl;
import ru.ancap.framework.plugin.api.configuration.language.AncapMultiLanguageConfiguration;
import ru.ancap.framework.plugin.api.configuration.language.Language;
import ru.ancap.framework.plugin.api.incubator.commands.CommandExecutor;
import ru.ancap.framework.plugin.api.plugins.info.AncapPluginSettings;

import java.util.List;
import java.util.Set;

public abstract class AncapPlugin extends AncapMinimalisticPlugin {

    private Metrics metrics;
    private AncapMultiLanguageConfiguration configuration;
    private AncapPluginSettings settings;

    protected Metrics getMetrics() {
        return this.metrics;
    }

    protected AncapMultiLanguageConfiguration getMultiLanguageConfiguration() {
        return this.configuration;
    }

    @Override
    public void onEnable() {
        super.onEnable();
        this.loadPluginSettings();
        this.loadMessagesConfiguration();
        this.registerMetrics();
    }

    /**
     * It's bad practice to use onDisable logic, because it might be not called in some
     * cases like server crash, so I force-disabled it in AncapFramework.
     */

    @Override
    public final void onDisable() {
        throw new UnsupportedOperationException();
    }

    public AncapPluginSettings getSettings() {
        return this.settings;
    }

    private void loadPluginSettings() {
        this.settings = new AncapPluginSettings(
                new PreAncapConfiguration(this.getSoftResourceSource(), "ancapplugin")
                        .getPrepared()
        );
    }

    protected void loadMessagesConfiguration() {
        this.configuration = new AncapMultiLanguageConfigurationBuilderImpl(
                this.getDataFolder(),
                this.getResourceSource(),
                this.getSettings().getMainLanguage())
                .setConfigName("messages")
                .setLanguagesSet(this.getLanguages())
                .build();
    }

    protected void registerMetrics() {
        this.metrics = new Metrics(this, this.getPluginId());
        // metrics.enable();
    }

    protected Set<Language> getLanguages() {
        return this.getSettings().getLanguages();
    }

    protected int getPluginId() {
        return this.getSettings().getPluginId();
    }

    @Override
    protected abstract List<Listener> getListeners();
    @Override
    protected abstract List<CommandExecutor> getExecutors();

}
