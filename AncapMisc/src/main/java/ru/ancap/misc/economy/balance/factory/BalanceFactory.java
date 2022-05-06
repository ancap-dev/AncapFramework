package ru.ancap.misc.economy.balance.factory;

import ru.ancap.misc.economy.balance.Balance;
import ru.ancap.misc.economy.balance.factory.exception.InvalidBalanceMapException;
import ru.ancap.misc.economy.balance.prebalance.exception.InvalidParsedStringException;
import ru.ancap.misc.economy.wallet.Wallet;
import ru.ancap.misc.strings.parsed.ParsedString;

import java.util.List;
import java.util.Map;

public interface BalanceFactory {

    Balance buildFrom(String string) throws InvalidParsedStringException;
    Balance buildFrom(ParsedString parsedString) throws InvalidParsedStringException;
    Balance buildFrom(Map<String, String> map) throws InvalidBalanceMapException;
    Balance buildFrom(List<Wallet> wallets);
}
