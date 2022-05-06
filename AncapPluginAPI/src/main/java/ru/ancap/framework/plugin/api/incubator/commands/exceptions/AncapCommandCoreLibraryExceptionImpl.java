package ru.ancap.framework.plugin.api.incubator.commands.exceptions;

import ru.ancap.framework.plugin.api.packetapi.packet.Sendable;

public class AncapCommandCoreLibraryExceptionImpl extends AncapCommandCoreLibraryException {

    public AncapCommandCoreLibraryExceptionImpl(AncapCommandCoreLibraryException exception) {
        super(exception);
    }

    @Override
    public Sendable getErrorNotifier() {
        throw new UnsupportedOperationException();
    }
}
