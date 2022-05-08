package ru.ancap.framework.plugin.api.configuration.extended;

import org.bukkit.configuration.ConfigurationSection;
import ru.ancap.framework.plugin.api.packet.api.packet.builder.PacketBuilderSourceImpl;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.actionbar.builder.ActionBarBuilderSourceImpl;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.bossbar.builder.BossBarBuilderSourceImpl;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.message.builder.MessageBuilderSourceImpl;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.sound.builder.SoundBuilderSourceImpl;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.title.builder.TitleBuilderSourceImpl;
import ru.ancap.misc.economy.balance.factory.BalanceFactoryImpl;
import ru.ancap.misc.placeholder.PlaceholderSource;

public class AncapClassicExtendedConfigurationSection extends AncapExtendedConfigurationSection {

    public AncapClassicExtendedConfigurationSection(ConfigurationSection configurationSection,
                                                    PlaceholderSource source) {
        super(configurationSection,
                source,
                new BalanceFactoryImpl(),
                new PacketBuilderSourceImpl(),
                new MessageBuilderSourceImpl(),
                new ActionBarBuilderSourceImpl(),
                new SoundBuilderSourceImpl(),
                new BossBarBuilderSourceImpl(),
                new TitleBuilderSourceImpl()
        );
    }

    public AncapClassicExtendedConfigurationSection(ConfigurationSection configurationSection) {
        super(configurationSection,
                new BalanceFactoryImpl(),
                new PacketBuilderSourceImpl(),
                new MessageBuilderSourceImpl(),
                new ActionBarBuilderSourceImpl(),
                new SoundBuilderSourceImpl(),
                new BossBarBuilderSourceImpl(),
                new TitleBuilderSourceImpl()
        );
    }
}
