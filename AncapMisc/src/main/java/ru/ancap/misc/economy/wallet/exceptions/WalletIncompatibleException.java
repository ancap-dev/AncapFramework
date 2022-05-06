package ru.ancap.misc.economy.wallet.exceptions;

import ru.ancap.misc.economy.wallet.Wallet;

public class WalletIncompatibleException extends RuntimeException {

    private Wallet firstWallet;
    private Wallet secondWallet;

    public WalletIncompatibleException(Wallet firstWallet, Wallet secondWallet) {
        this.firstWallet = firstWallet;
        this.secondWallet = secondWallet;
    }

    public Wallet getFirstWallet() {
        return firstWallet;
    }

    public Wallet getSecondWallet() {
        return secondWallet;
    }
}
