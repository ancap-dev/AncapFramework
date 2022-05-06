package ru.ancap.misc.economy.currency;

public class AncapCurrency implements Currency {

    private String name;

    public AncapCurrency(String name) {
        this.name = name;
    }

    public AncapCurrency(AncapCurrency currency) {
        this(currency.name);
    }

    public String getName() {
        return name;
    }

    public boolean equals(Currency currency) {
        return this.getName().equals(currency.getName());
    }
}
