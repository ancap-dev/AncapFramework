package ru.ancap.framework.plugin.api.configuration.placeholder;

import ru.ancap.misc.placeholder.Placeholder;
import ru.ancap.misc.placeholder.PlaceholderSource;
import ru.ancap.misc.placeholder.PlaceholderableString;
import ru.ancap.misc.placeholder.exception.NoSuchPlaceholderException;

import java.util.ArrayList;
import java.util.List;

public class AncapPlaceholderableString implements PlaceholderableString {

    private String string;
    private PlaceholderSource source;
    private String separator;

    public AncapPlaceholderableString(String string, PlaceholderSource source) {
        this(string, source, "%");
    }

    public AncapPlaceholderableString(String string, PlaceholderSource source, String separator) {
        this.string = string;
        this.source = source;
    }

    public AncapPlaceholderableString(AncapPlaceholderableString string) {
        this(string.getString(), string.getSource());
    }

    protected String getString() {
        return this.string;
    }
    protected PlaceholderSource getSource() {
        return this.source;
    }
    protected String getSeparator(){
        return this.separator;
    }

    @Override
    public String getPlaceholdered() throws NoSuchPlaceholderException {
        List<Placeholder> placeholders = this.getPlaceholders();
        if (placeholders.isEmpty()) {
            return this.string;
        }
        for (Placeholder placeholder : placeholders) {
            string = string.replace(placeholder.getStringForReplace(), source.getValue(placeholder));
        }
        return string;
    }

    public List<Placeholder> getPlaceholders() {
        String separator = this.getSeparator();
        List<Placeholder> placeholders = new ArrayList<>();
        List<Character> nextPlaceholder = new ArrayList<>();
        boolean placeholderOpened = false;
        char[] chars = this.string.toCharArray();
        for (char ch : chars) {
            String character = String.valueOf(ch);
            if (placeholderOpened) {
                if (character.equalsIgnoreCase(separator)) {
                    String string = nextPlaceholder.stream()
                            .map(Object::toString)
                            .reduce((acc, e) -> acc  + e)
                            .get();
                    nextPlaceholder = new ArrayList<>();
                    placeholders.add(new AncapPlaceholder(string));
                    placeholderOpened = false;
                    continue;
                }
                nextPlaceholder.add(ch);
            } else {
                if (character.equalsIgnoreCase(separator)) {
                    placeholderOpened = true;
                }
            }
        }
        return placeholders;
    }

}
