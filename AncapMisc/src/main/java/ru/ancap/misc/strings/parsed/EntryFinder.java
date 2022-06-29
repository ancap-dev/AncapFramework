package ru.ancap.misc.strings.parsed;

import java.util.ArrayList;
import java.util.List;

public class EntryFinder {

    private final String string;
    private final String openingSeparator;
    private final String closingSeparator;

    private List<String> entries = new ArrayList<>();
    private List<Character> nextEntry = new ArrayList<>();
    private boolean entryOpened = false;
    private char[] chars;

    public EntryFinder(String string, String separator) {
        this(string, separator, separator);
    }

    public EntryFinder(String string, String openingSeparator, String closingSeparator) {
        this.string = string;
        this.openingSeparator = openingSeparator;
        this.closingSeparator = closingSeparator;
    }

    public List<String> find() {
        this.chars = this.string.toCharArray();
        return this.processChars();
    }

    private List<String> processChars() {
        for (char ch : chars) {
            this.processChar(ch);
        }
        return this.entries;
    }

    private void processChar(char ch) {
        if (this.entryOpened) {
            this.processCharInEntry(ch);
            return;
        }
        this.processCharOutEntry(ch);
    }

    private void processCharOutEntry(char ch) {
        if (String.valueOf(ch).equalsIgnoreCase(openingSeparator)) {
            entryOpened = true;
        }
    }

    private void processCharInEntry(char ch) {
        if (String.valueOf(ch).equalsIgnoreCase(this.closingSeparator)) {
            this.closeEntry();
        }
        this.nextEntry.add(ch);
    }

    private void closeEntry() {
        String string = this.nextEntry.stream()
                .map(Object::toString)
                .reduce((acc, e) -> acc  + e)
                .get();
        this.nextEntry = new ArrayList<>();
        this.entries.add(string);
        this.entryOpened = false;
    }
}
