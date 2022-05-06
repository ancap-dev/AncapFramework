package ru.ancap.framework.plugin.api.configuration.placeholder;

import ru.ancap.misc.placeholder.Placeholder;

public class AncapPlaceholder implements Placeholder {

    private String string;

    public AncapPlaceholder(String string) {
        this.string = string;
    }

    public AncapPlaceholder(AncapPlaceholder placeholder) {
        this.string = placeholder.getString();
    }

    protected String getString() {
        return this.string;
    }

    @Override
    public String getStringForReplace() {
        return "%"+this.string+"%";
    }

    @Override
    public String getName() {
        return this.string.toUpperCase();
    }
}
