package ru.ancap.framework.plugin.api.incubator.commands.exceptions;

import ru.ancap.framework.plugin.api.packet.api.packet.Sendable;

public class AncapCommandNotEnoughArgsException extends AncapCommandCoreLibraryException {

    public AncapCommandNotEnoughArgsException(AncapCommandCoreLibraryException exception) {
        super(exception);
    }

    /*
    @Override
    public Sendable getErrorNotifier() {
        return this.getCoreLibraryErrorNotifierSource().getInvalidArgsCountPacket();
    }

     */

    @Override
    public Sendable getErrorNotifier() {
        throw new UnsupportedOperationException();
    }
}
