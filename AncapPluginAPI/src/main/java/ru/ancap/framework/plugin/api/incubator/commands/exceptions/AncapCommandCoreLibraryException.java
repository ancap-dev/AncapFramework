package ru.ancap.framework.plugin.api.incubator.commands.exceptions;

import ru.ancap.framework.plugin.coreplugin.CoreLibraryErrorNotifierSource;

public abstract class AncapCommandCoreLibraryException extends AncapCommandException {

    private CoreLibraryErrorNotifierSource coreLibraryErrorNotifierSource;

    public AncapCommandCoreLibraryException(AncapCommandException exception, CoreLibraryErrorNotifierSource source) {
        super(exception);
        this.coreLibraryErrorNotifierSource = source;
    }

    public AncapCommandCoreLibraryException(AncapCommandCoreLibraryException exception) {
        this(exception, exception.getCoreLibraryErrorNotifierSource());
    }

    protected CoreLibraryErrorNotifierSource getCoreLibraryErrorNotifierSource() {
        return this.coreLibraryErrorNotifierSource;
    }
}
