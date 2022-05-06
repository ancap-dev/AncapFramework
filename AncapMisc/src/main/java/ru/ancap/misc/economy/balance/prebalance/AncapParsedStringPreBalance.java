package ru.ancap.misc.economy.balance.prebalance;

import ru.ancap.misc.economy.balance.AncapBalance;
import ru.ancap.misc.economy.balance.Balance;
import ru.ancap.misc.economy.balance.prebalance.exception.InvalidParsedStringException;
import ru.ancap.misc.economy.currency.AncapCurrency;
import ru.ancap.misc.economy.currency.Currency;
import ru.ancap.misc.economy.wallet.AncapWallet;
import ru.ancap.misc.economy.wallet.Wallet;
import ru.ancap.misc.strings.parsed.ParsedString;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AncapParsedStringPreBalance implements PreBalance {

    private ParsedString string;

    public AncapParsedStringPreBalance(ParsedString string) {
        this.string = string;
    }

    public AncapParsedStringPreBalance(AncapParsedStringPreBalance parsedStringPreBalance) {
        this(parsedStringPreBalance.getParsedString());
    }

    protected ParsedString getParsedString() {
        return this.string;
    }

    public Balance getPrepared() {
        Set<String> currencyNames = string.getKeys();
        List<Wallet> wallets = new ArrayList<>();
        for (String currencyName : currencyNames) {
            Currency currency = new AncapCurrency(currencyName);
            double amount = this.getCurrencyAmount(currencyName);
            Wallet wallet = new AncapWallet(currency, amount);
            wallets.add(wallet);
        }
        return new AncapBalance(wallets);
    }

    private Double getCurrencyAmount(String type) {
        String value = this.string.getValue(type);
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new InvalidParsedStringException(value);
        } catch (NullPointerException e) {
            throw new InvalidParsedStringException(""+value);
        }
    }
}
