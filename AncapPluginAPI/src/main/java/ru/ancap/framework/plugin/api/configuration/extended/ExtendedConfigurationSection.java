package ru.ancap.framework.plugin.api.configuration.extended;

import org.jetbrains.annotations.NotNull;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.exceptions.InvalidConfigurationBalanceException;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.exceptions.InvalidConfigurationMapException;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.exceptions.InvalidConfigurationSendableException;
import ru.ancap.framework.plugin.api.configuration.placeholder.PlaceholderedConfigurationSection;
import ru.ancap.framework.plugin.api.packet.api.packet.Sendable;
import ru.ancap.misc.economy.balance.Balance;

import java.util.Map;

public interface ExtendedConfigurationSection extends PlaceholderedConfigurationSection {

    Balance getBalance() throws InvalidConfigurationBalanceException;
    Balance getBalance(String path) throws InvalidConfigurationBalanceException;
    Sendable getPacket() throws InvalidConfigurationSendableException;
    Sendable getPacket(String path) throws InvalidConfigurationSendableException;
    Map<String, String> getMap() throws InvalidConfigurationMapException;

    Map<String, String> getMap(String path) throws InvalidConfigurationMapException;

    @Override
    ExtendedConfigurationSection getConfigurationSection(@NotNull String path);

}
