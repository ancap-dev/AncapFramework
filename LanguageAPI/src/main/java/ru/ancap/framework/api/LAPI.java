package ru.ancap.framework.api;

import ru.ancap.framework.api.language.Language;
import ru.ancap.framework.api.language.LanguageSettings;
import ru.ancap.framework.api.locale.Locales;

public class LAPI {

    private static Locales locales;
    private static LanguageSettings settings;

    public static void setup(Locales locales, LanguageSettings settings) {
        LAPI.locales = locales;
        LAPI.settings = settings;
    }

    public static void loadLocale(String id, String localized, Language language) {
        locales.loadLocale(id, localized, language);
    }

    public static String localized(String id, String playerID) {
        return locales.localized(id, settings.getLanguage(playerID));
    }

    public static void setupLanguage(String playerID, Language language) {
        settings.setLanguage(playerID, language);
    }

}
