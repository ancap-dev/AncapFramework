package ru.ancap.misc.economy.balance;


import ru.ancap.misc.economy.wallet.Wallet;
import ru.ancap.misc.economy.wallet.exceptions.NotEnoughMoneyException;

import java.util.ArrayList;
import java.util.List;

public class AncapBalance implements Balance {

    private List<Wallet> wallets;

    public AncapBalance() {
        this.wallets = new ArrayList<>();
    }

    public AncapBalance(List<Wallet> wallets) {
        this.wallets = wallets;
    }

    public AncapBalance(AncapBalance balance) {
        this(balance.wallets);
    }

    public List<Wallet> getWallets() {
        return this.wallets;
    }

    public boolean have(Balance balance) {
        for (Wallet wallet : balance.getWallets()) {
            if (!this.have(wallet)) {
                return false;
            }
        }
        return true;
    }

    public void remove(Balance balance) throws NotEnoughMoneyException {
        for (Wallet wallet : balance.getWallets()) {
            this.remove(wallet);
        }
    }

    public void add(Balance balance) {
        for (Wallet wallet : balance.getWallets()) {
            this.add(wallet);
        }
    }

    public void multiply(double multiplier) {
        for (Wallet wallet : this.getWallets()) {
            wallet.multiply(multiplier);
        }
    }

    public void replace(Balance balance) {
        this.wallets = balance.getWallets();
    }

    protected boolean have(Wallet wallet) {
        for (Wallet wallet1 : this.getWallets()) {
            if (wallet1.have(wallet)) {
                return true;
            }
        }
        return false;
    }

    protected void add(Wallet wallet) {
        for (Wallet wallet1 : this.wallets) {
            if (wallet1.isSameCurrencyWith(wallet)) {
                wallet1.add(wallet);
                return;
            }
            this.wallets.add(wallet);
        }
    }

    protected void remove(Wallet wallet) throws NotEnoughMoneyException {
        for (Wallet wallet1 : this.wallets) {
            if (wallet1.getCurrency().equals(wallet.getCurrency())) {
                wallet1.remove(wallet);
                return;
            }
            throw new NotEnoughMoneyException();
        }
    }
}
