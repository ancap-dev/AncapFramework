package ru.ancap.misc.strings.parsed;

import java.util.Set;

public interface ParsedString {

    Set<String> getKeys();
    String getValue(String key);
}
