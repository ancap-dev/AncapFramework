package ru.ancap.framework.plugin.api.configuration.language;

import java.util.Objects;

public class AncapLanguage implements Language {

    private String code;

    public AncapLanguage(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AncapLanguage)) return false;
        AncapLanguage that = (AncapLanguage) o;
        return Objects.equals(code, that.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public String getName() {
        return this.code;
    }
}
