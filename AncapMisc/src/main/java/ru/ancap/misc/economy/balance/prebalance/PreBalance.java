package ru.ancap.misc.economy.balance.prebalance;

import ru.ancap.misc.economy.balance.Balance;
import ru.ancap.misc.preparable.Preparable;

public interface PreBalance extends Preparable {

    Balance getPrepared();

}
