package ru.ancap.misc.strings;

public interface Placeholderable extends Replaceable {

    default void setPlaceholder(String placeholder, String value) {
        this.replace("%"+placeholder+"%", value);
    }
}
