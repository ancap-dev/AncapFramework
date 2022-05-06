package ru.ancap.misc.placeholder;

import ru.ancap.misc.placeholder.exception.NoSuchPlaceholderException;

import java.util.Map;

public class BuiltPlaceholderSource implements PlaceholderSource {

    private Map<String, String> map;

    public BuiltPlaceholderSource(Map<String, String> map) {
        this.map = map;
    }

    public BuiltPlaceholderSource(BuiltPlaceholderSource source) {
        this(source.getMap());
    }

    protected Map<String, String> getMap() {
        return this.map;
    }

    @Override
    public String getValue(Placeholder placeholder) throws NoSuchPlaceholderException {
        String value = this.map.get(placeholder.getName());
        if (value == null) {
            throw new NoSuchPlaceholderException(placeholder);
        }
        return value;
    }
}
