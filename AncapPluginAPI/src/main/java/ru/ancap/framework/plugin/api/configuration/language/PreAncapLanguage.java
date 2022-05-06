package ru.ancap.framework.plugin.api.configuration.language;

import ru.ancap.misc.preparable.Preparable;

public class PreAncapLanguage implements Preparable {

    private String string;

    public PreAncapLanguage(String string) {
        this.string = string;
    }

    @Override
    public Language getPrepared() {
        return new AncapLanguage(string.toLowerCase());
    }
}
