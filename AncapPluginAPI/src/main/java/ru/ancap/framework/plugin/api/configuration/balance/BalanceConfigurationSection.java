package ru.ancap.framework.plugin.api.configuration.balance;

import ru.ancap.framework.plugin.api.configuration.exception.InvalidConfigurationBalanceException;
import ru.ancap.misc.economy.balance.Balance;

public interface BalanceConfigurationSection {

    Balance getBalance() throws InvalidConfigurationBalanceException;
}
