package ru.ancap.framework.plugin.api.configuration.extended.entensions.impl;

import ru.ancap.framework.plugin.api.configuration.AncapWrappedConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.api.SendableConfigurationSection;
import ru.ancap.framework.plugin.api.configuration.extended.entensions.exceptions.InvalidConfigurationSendableException;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.message.Message;
import ru.ancap.framework.plugin.api.packet.api.packet.modules.message.builder.MessageBuilderSource;

public class AncapMessageConfigurationSection extends AncapWrappedConfigurationSection implements SendableConfigurationSection {

    private MessageBuilderSource messageBuilderSource;

    public AncapMessageConfigurationSection(AncapWrappedConfigurationSection section,
                                            MessageBuilderSource messageBuilderSource) {
        super(section);
        this.messageBuilderSource = messageBuilderSource;
    }

    public AncapMessageConfigurationSection(AncapMessageConfigurationSection section) {
        this(section, section.getMessageBuilderSource());
    }

    protected MessageBuilderSource getMessageBuilderSource() {
        return this.messageBuilderSource;
    }

    @Override
    public Message getSendable() throws InvalidConfigurationSendableException {
        return null;
    }
}
