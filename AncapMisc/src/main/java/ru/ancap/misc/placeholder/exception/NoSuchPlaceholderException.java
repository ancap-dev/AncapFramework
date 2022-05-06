package ru.ancap.misc.placeholder.exception;

import ru.ancap.misc.placeholder.Placeholder;

public class NoSuchPlaceholderException extends Exception {

    private Placeholder placeholder;

    public NoSuchPlaceholderException(Placeholder placeholder) {
        this.placeholder = placeholder;
    }

    public Placeholder getPlaceholder() {
        return this.placeholder;
    }
}
