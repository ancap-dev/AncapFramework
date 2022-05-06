package ru.ancap.misc.placeholder;

import ru.ancap.misc.placeholder.exception.NoSuchPlaceholderException;

public interface PlaceholderSource {

    String getValue(Placeholder placeholder) throws NoSuchPlaceholderException;
}
