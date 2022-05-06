package ru.ancap.misc.economy.wallet.factory;

import ru.ancap.misc.economy.currency.AncapCurrency;
import ru.ancap.misc.economy.currency.Currency;
import ru.ancap.misc.economy.wallet.AncapWallet;
import ru.ancap.misc.economy.wallet.Wallet;
import ru.ancap.misc.economy.wallet.factory.exception.InvalidWalletDataException;

public class AncapWalletFactory implements WalletFactory {

    @Override
    public Wallet buildFrom(String stringCurrency, String amount) throws InvalidWalletDataException {
        Currency currency = new AncapCurrency(stringCurrency);
        return this.buildFrom(currency, amount);
    }

    @Override
    public Wallet buildFrom(Currency currency, String stringAmount) throws InvalidWalletDataException {
        double amount;
        try {
            amount = Double.parseDouble(stringAmount);
        } catch (NumberFormatException e) {
            throw new InvalidWalletDataException(stringAmount);
        } catch (NullPointerException e) {
            throw new InvalidWalletDataException(""+stringAmount);
        }
        return this.buildFrom(currency, amount);
    }

    @Override
    public Wallet buildFrom(Currency currency, double amount) {
        return new AncapWallet(currency, amount);
    }
}
