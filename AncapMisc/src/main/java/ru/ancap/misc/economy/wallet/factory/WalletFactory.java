package ru.ancap.misc.economy.wallet.factory;

import ru.ancap.misc.economy.currency.Currency;
import ru.ancap.misc.economy.wallet.Wallet;
import ru.ancap.misc.economy.wallet.factory.exception.InvalidWalletDataException;

public interface WalletFactory {

    Wallet buildFrom(String currency, String amount) throws InvalidWalletDataException;
    Wallet buildFrom(Currency currency, String amount) throws InvalidWalletDataException;
    Wallet buildFrom(Currency currency, double amount);
}
