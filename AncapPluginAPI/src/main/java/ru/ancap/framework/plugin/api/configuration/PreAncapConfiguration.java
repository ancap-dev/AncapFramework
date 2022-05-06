package ru.ancap.framework.plugin.api.configuration;

import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.YamlConfiguration;
import ru.ancap.framework.plugin.api.plugins.ResourceSource;
import ru.ancap.framework.plugin.api.packetapi.packet.builder.AncapPacketBuilderSourceImpl;
import ru.ancap.misc.economy.balance.factory.BalanceFactoryImpl;
import ru.ancap.misc.placeholder.PlaceholderSourceBuilderSourceImpl;
import ru.ancap.misc.preparable.Preparable;

import java.io.File;
import java.io.InputStreamReader;

public class PreAncapConfiguration implements Preparable {

    protected static class Extension {
        public static final String YML = ".yml";
    }

    private Configuration configuration;

    public PreAncapConfiguration(File dataFolder, String name) {
        this.configuration = YamlConfiguration.loadConfiguration(
                new File(dataFolder, name+Extension.YML)
        );
    }

    public PreAncapConfiguration(ResourceSource source, String fileName) {
        this.configuration = YamlConfiguration.loadConfiguration(
                new InputStreamReader(
                        source.getResource(fileName+Extension.YML)
                )
        );
    }

    public PreAncapConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    protected Configuration getConfiguration() {
        return this.configuration;
    }

    @Override
    public AncapConfiguration getPrepared() {
        return new AncapConfigurationImpl(
                this.getConfiguration(),
                new AncapPacketBuilderSourceImpl(),
                new BalanceFactoryImpl(),
                new PlaceholderSourceBuilderSourceImpl()
        );
    }
}
