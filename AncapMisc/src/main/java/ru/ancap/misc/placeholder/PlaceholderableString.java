package ru.ancap.misc.placeholder;

import ru.ancap.misc.placeholder.exception.NoSuchPlaceholderException;

public interface PlaceholderableString {

    String getPlaceholdered() throws NoSuchPlaceholderException;

}
