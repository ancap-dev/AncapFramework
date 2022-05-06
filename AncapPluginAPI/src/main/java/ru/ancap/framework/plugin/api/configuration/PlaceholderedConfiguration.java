package ru.ancap.framework.plugin.api.configuration;

import ru.ancap.framework.plugin.api.configuration.exception.InvalidConfigurationBalanceException;
import ru.ancap.framework.plugin.api.configuration.exception.InvalidConfigurationPacketException;
import ru.ancap.framework.plugin.api.packetapi.packet.Packet;
import ru.ancap.misc.economy.balance.Balance;
import ru.ancap.misc.placeholder.PlaceholderSource;
import ru.ancap.misc.placeholder.exception.NoSuchPlaceholderException;

import java.util.List;

public interface PlaceholderedConfiguration extends Configuration {

    Packet getPacket(String path, PlaceholderSource source) throws InvalidConfigurationPacketException;
    Balance getBalance(String path, PlaceholderSource source) throws InvalidConfigurationBalanceException;
    String getString(String path, PlaceholderSource source) throws NoSuchPlaceholderException;
    List<String> getStringList(String path, PlaceholderSource source) throws NoSuchPlaceholderException;

}
