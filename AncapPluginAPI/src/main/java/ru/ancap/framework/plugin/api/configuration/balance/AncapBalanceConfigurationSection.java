package ru.ancap.framework.plugin.api.configuration.balance;

import ru.ancap.framework.plugin.api.configuration.AncapPlaceholderedConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.exception.InvalidConfigurationBalanceException;
import ru.ancap.misc.economy.balance.Balance;
import ru.ancap.misc.economy.balance.factory.BalanceFactory;
import ru.ancap.misc.economy.balance.factory.exception.InvalidBalanceMapException;

import java.util.Map;

public class AncapBalanceConfigurationSection extends AncapPlaceholderedConfigurationSection implements BalanceConfigurationSection {

    private BalanceFactory factory;

    public AncapBalanceConfigurationSection(AncapPlaceholderedConfigurationSection section, BalanceFactory factory) {
        super(section);
        this.factory = factory;
    }

    public AncapBalanceConfigurationSection(AncapBalanceConfigurationSection section) {
        this(section, section.getBalanceFactory());
    }

    protected BalanceFactory getBalanceFactory() {
        return this.factory;
    }

    @Override
    public Balance getBalance() throws InvalidConfigurationBalanceException {
        Map<String, String> map = this.getMap();
        try {
            return this.factory.buildFrom(map);
        } catch (InvalidBalanceMapException e) {
            throw new InvalidConfigurationBalanceException(this.getCurrentPath(), e.getInvalidData()+" is invalid data");
        }
    }
}
