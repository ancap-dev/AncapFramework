package ru.ancap.framework.plugin.api.configuration;

import ru.ancap.framework.plugin.api.configuration.exception.InvalidConfigurationBalanceException;
import ru.ancap.framework.plugin.api.configuration.exception.InvalidConfigurationPacketException;
import ru.ancap.framework.plugin.api.packet.api.packet.Packet;
import ru.ancap.misc.economy.balance.Balance;

public interface Configuration extends org.bukkit.configuration.Configuration {

    Packet getPacket(String path) throws InvalidConfigurationPacketException;
    Balance getBalance(String path) throws InvalidConfigurationBalanceException;
}
