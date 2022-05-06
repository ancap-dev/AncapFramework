package ru.ancap.misc.economy.wallet;

import ru.ancap.misc.economy.currency.Currency;
import ru.ancap.misc.economy.wallet.exceptions.NotEnoughMoneyException;

public interface Wallet {

    Currency getCurrency();
    double getAmount();

    void replace(Wallet wallet);

    void add(Wallet wallet);
    void remove(Wallet wallet) throws NotEnoughMoneyException;
    void multiply(double multiplier);

    boolean have(Wallet wallet);
    boolean isSameCurrencyWith(Wallet wallet);

    void checkCompatibilityWith(Wallet wallet);
}
