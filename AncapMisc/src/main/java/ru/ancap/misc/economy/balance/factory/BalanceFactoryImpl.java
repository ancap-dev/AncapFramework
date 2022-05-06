package ru.ancap.misc.economy.balance.factory;

import ru.ancap.misc.economy.balance.AncapBalance;
import ru.ancap.misc.economy.balance.Balance;
import ru.ancap.misc.economy.balance.factory.exception.InvalidBalanceMapException;
import ru.ancap.misc.economy.balance.prebalance.AncapParsedStringPreBalance;
import ru.ancap.misc.economy.balance.prebalance.exception.InvalidParsedStringException;
import ru.ancap.misc.economy.wallet.Wallet;
import ru.ancap.misc.economy.wallet.factory.AncapWalletFactory;
import ru.ancap.misc.economy.wallet.factory.WalletFactory;
import ru.ancap.misc.economy.wallet.factory.exception.InvalidWalletDataException;
import ru.ancap.misc.strings.parsed.AncapPreParsedString;
import ru.ancap.misc.strings.parsed.ParsedString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BalanceFactoryImpl implements BalanceFactory {

    private WalletFactory walletFactory;

    public BalanceFactoryImpl() {
        this.walletFactory = new AncapWalletFactory();
    }

    public BalanceFactoryImpl(WalletFactory factory) {
        this.walletFactory = factory;
    }

    public BalanceFactoryImpl(BalanceFactoryImpl factory) {
        this(factory.getWalletFactory());
    }

    protected WalletFactory getWalletFactory() {
        return this.walletFactory;
    }

    public void setCustomWalletFactory(WalletFactory factory) {
        this.walletFactory = factory;
    }

    @Override
    public Balance buildFrom(String string) throws InvalidParsedStringException {
        ParsedString parsedString = new AncapPreParsedString(string).parse(";", ":");
        return this.buildFrom(parsedString);
    }

    @Override
    public Balance buildFrom(ParsedString parsedString) throws InvalidParsedStringException {
        return new AncapParsedStringPreBalance(parsedString).getPrepared();
    }

    @Override
    public Balance buildFrom(Map<String, String> map) throws InvalidBalanceMapException {
        List<Wallet> wallets = new ArrayList<>();
        for (String string : map.keySet()) {
            try {
                wallets.add(this.walletFactory.buildFrom(string, string));
            } catch (InvalidWalletDataException e) {
                throw new InvalidBalanceMapException(e.getInvalidData());
            }
        }
        return this.buildFrom(wallets);
    }

    @Override
    public Balance buildFrom(List<Wallet> wallets) {
        return new AncapBalance(wallets);
    }
}
