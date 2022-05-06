package ru.ancap.framework.plugin.api.configuration;

import org.bukkit.configuration.Configuration;
import ru.ancap.framework.plugin.api.packet.api.packet.builder.AncapPacketBuilderSource;
import ru.ancap.misc.economy.balance.factory.BalanceFactory;
import ru.ancap.misc.placeholder.PlaceholderSourceBuilderSource;

public class AncapConfigurationImpl extends AncapConfiguration {

    public AncapConfigurationImpl(Configuration configuration,
                                  AncapPacketBuilderSource packetBuilderSource,
                                  BalanceFactory factory,
                                  PlaceholderSourceBuilderSource source) {
        super(configuration, packetBuilderSource, factory, source);
    }
}
