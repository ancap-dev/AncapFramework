package ru.ancap.framework.plugin.api.incubator.commands.exceptions;

import ru.ancap.framework.plugin.api.packet.api.packet.Sendable;

public class AncapCommandExceptionImpl extends AncapCommandException {

    public AncapCommandExceptionImpl(AncapCommandException exception) {
        super(exception);
    }

    @Override
    public Sendable getErrorNotifier() {
        throw new UnsupportedOperationException();
    }
}
