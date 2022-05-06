package ru.ancap.misc.placeholder;

import java.util.List;

public interface PlaceholderSourceBuilder {

    PlaceholderSourceBuilder addPlaceholder(String placeholder, List<String> value, String separator);
    PlaceholderSourceBuilder addPlaceholder(String placeholder, Integer value);
    PlaceholderSourceBuilder addPlaceholder(String placeholder, String value);
    PlaceholderSourceBuilder addSource(PlaceholderSource source);
    PlaceholderSource build();
}
