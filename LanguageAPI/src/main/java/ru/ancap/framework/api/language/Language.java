package ru.ancap.framework.api.language;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class Language {

    public static final Language RUSSIAN = of("ru");
    public static final Language ENGLISH = of("en");
    public static final Language GERMAN = of("de");
    public static final Language PIGS = of("uk");
    public static final Language FRENCH = of("fr");
    public static final Language JAPANESE = of("ja");
    public static final Language SPANISH = of("es");
    public static final Language CHINESE = of("zh");

    private static Map<String, Language> map;

    private final String code;

    private Language(String code) {
        this.code = code;
    }

    public static Language of(@NotNull String languageCode) {
        if (map == null) {
            map = new HashMap<>();
        }
        if (map.get(languageCode) == null) {
            Language language = new Language(languageCode);
            map.put(languageCode, language);
            return language;
        }
        return map.get(languageCode);
    }
}
