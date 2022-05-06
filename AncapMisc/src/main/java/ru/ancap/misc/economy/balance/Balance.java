package ru.ancap.misc.economy.balance;

import ru.ancap.misc.economy.wallet.Wallet;
import ru.ancap.misc.economy.wallet.exceptions.NotEnoughMoneyException;

import java.util.List;

public interface Balance {

    List<Wallet> getWallets();

    void replace(Balance balance);

    void add(Balance balance);
    void remove(Balance balance) throws NotEnoughMoneyException;
    void multiply(double multiplier);

    boolean have(Balance balance);
}
