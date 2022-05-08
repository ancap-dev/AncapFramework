package ru.ancap.framework.plugin.api.configuration.extended.entensions.impl;

import ru.ancap.framework.plugin.api.configuration.AncapWrappedConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.api.BalanceConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.exceptions.InvalidConfigurationBalanceException;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.exceptions.InvalidConfigurationMapException;
import ru.ancap.misc.economy.balance.Balance;
import ru.ancap.misc.economy.balance.factory.BalanceFactory;
import ru.ancap.misc.economy.balance.factory.exception.InvalidBalanceMapException;

import java.util.Map;

public class AncapBalanceConfigurationSection extends AncapWrappedConfigurationSection implements BalanceConfigurationSection {

    private BalanceFactory factory;

    public AncapBalanceConfigurationSection(AncapWrappedConfigurationSection section, BalanceFactory factory) {
        super(section);
        this.factory = factory;
    }

    public AncapBalanceConfigurationSection(AncapBalanceConfigurationSection section) {
        this(section, section.getFactory());
    }

    protected BalanceFactory getFactory() {
        return this.factory;
    }

    @Override
    public Balance getBalance() throws InvalidConfigurationBalanceException {
        Map<String, String> map;
        try {
            map = new AncapMapConfigurationSection(this.getSection()).getMap();
        } catch (InvalidConfigurationMapException e) {
            throw new InvalidConfigurationBalanceException(e.getPath(), e.getReason());
        }
        try {
            return this.factory.buildFrom(map);
        } catch (InvalidBalanceMapException e) {
            throw new InvalidConfigurationBalanceException(this.getSection().getCurrentPath(), e.getInvalidData()+" is invalid data");
        }
    }
}
