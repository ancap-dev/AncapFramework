package ru.ancap.misc.economy.wallet;

import ru.ancap.misc.economy.currency.Currency;
import ru.ancap.misc.economy.wallet.exceptions.WalletIncompatibleException;

public class AncapWallet implements Wallet {

    private Currency currency;
    private double amount;

    public AncapWallet(Currency currency, double amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public AncapWallet(AncapWallet wallet) {
        this(wallet.currency, wallet.amount);
    }

    public void replace(Wallet wallet) {
        this.currency = wallet.getCurrency();
        this.amount = wallet.getAmount();
    }

    public void add(Wallet wallet) {
        this.checkCompatibilityWith(wallet);
        this.add(wallet.getAmount());
    }

    public void remove(Wallet wallet) {
        this.checkCompatibilityWith(wallet);
        this.remove(wallet.getAmount());
    }

    public void multiply(double amount) {
        this.amount = this.amount*amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public double getAmount() {
        return amount;
    }

    public boolean have(Wallet wallet) {
        if (!this.currency.equals(wallet.getCurrency())) {
            return false;
        }
        return this.have(wallet.getAmount());
    }

    public boolean isSameCurrencyWith(Wallet wallet) {
        return wallet.getCurrency().equals(this.currency);
    }

    public void checkCompatibilityWith(Wallet wallet) {
        if (!wallet.isSameCurrencyWith(this)) {
            throw new WalletIncompatibleException(this, wallet);
        }
    }

    private void remove(double amount) {
        this.amount = this.amount - amount;
    }

    private void add(double amount) {
        this.amount = this.amount + amount;
    }

    private boolean have(double amount) {
        return this.amount >= amount;
    }
}
