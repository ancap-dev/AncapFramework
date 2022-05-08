package ru.ancap.framework.plugin.api.configuration.extended.entensions.impl;

import ru.ancap.framework.plugin.api.configuration.AncapWrappedConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.api.SendableConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.exceptions.InvalidConfigurationSendableException;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.actionbar.ActionBar;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.actionbar.builder.ActionBarBuilderSource;

public class AncapActionBarConfigurationSection extends AncapWrappedConfigurationSection implements SendableConfigurationSection {

    private ActionBarBuilderSource source;

    public AncapActionBarConfigurationSection(AncapWrappedConfigurationSection section, ActionBarBuilderSource actionBarBuilderSource) {
        super(section);
        this.source = actionBarBuilderSource;
    }

    public AncapActionBarConfigurationSection(AncapActionBarConfigurationSection section) {
        this(section, section.getActionBarBuilderSource());
    }

    protected ActionBarBuilderSource getActionBarBuilderSource() {
        return this.source;
    }

    @Override
    public ActionBar getSendable() throws InvalidConfigurationSendableException {
        return null;
    }
}
