package ru.ancap.framework.plugin.api.configuration.extended.entensions.api;

import ru.ancap.framework.plugin.api.configuration.extended.entensions.exceptions.InvalidConfigurationBalanceException;
import ru.ancap.misc.economy.balance.Balance;

public interface BalanceConfigurationSection {

    Balance getBalance() throws InvalidConfigurationBalanceException;

}
