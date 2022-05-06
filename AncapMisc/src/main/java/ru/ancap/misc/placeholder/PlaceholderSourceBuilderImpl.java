package ru.ancap.misc.placeholder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlaceholderSourceBuilderImpl implements PlaceholderSourceBuilder {

    private final List<PlaceholderSource> sources;
    private Map<String, String> map;

    public PlaceholderSourceBuilderImpl() {
        this.sources = new ArrayList<>();
        this.map = new HashMap<>();
    }

    @Override
    public PlaceholderSourceBuilder addPlaceholder(String placeholder, List<String> value, String separator) {
        this.addPlaceholder(placeholder, String.join(separator, value));
        return this;
    }

    @Override
    public PlaceholderSourceBuilder addPlaceholder(String placeholder, Integer value) {
        this.addPlaceholder(placeholder, value.toString());
        return this;
    }

    @Override
    public PlaceholderSourceBuilder addPlaceholder(String placeholder, String value) {
        this.map.put(placeholder, value);
        return this;
    }

    @Override
    public PlaceholderSourceBuilder addSource(PlaceholderSource source) {
        this.dump();
        this.sources.add(source);
        return this;
    }

    private void dump() {
        this.sources.add(
                new BuiltPlaceholderSource(this.map)
        );
    }

    @Override
    public PlaceholderSource build() {
        this.dump();
        return new PlaceholderSourceBundle(this.sources);
    }
}
