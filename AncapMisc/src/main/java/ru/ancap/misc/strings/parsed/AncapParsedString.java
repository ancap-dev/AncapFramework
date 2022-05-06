package ru.ancap.misc.strings.parsed;

import java.util.Map;
import java.util.Set;

public class AncapParsedString implements ParsedString {

    Map<String, String> parsedString;

    public AncapParsedString(Map<String, String> parsedString) {
        this.parsedString = parsedString;
    }

    public AncapParsedString(AncapParsedString string) {
        this(string.getParsedString());
    }

    protected Map<String, String> getParsedString() {
        return this.parsedString;
    }

    public Set<String> getKeys() {
        return this.parsedString.keySet();
    }

    public String getValue(String key) {
        return parsedString.get(key);
    }
}
