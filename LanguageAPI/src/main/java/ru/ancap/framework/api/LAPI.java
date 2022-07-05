package ru.ancap.framework.api;

import ru.ancap.framework.api.language.Language;
import ru.ancap.framework.api.language.LanguageSettings;
import ru.ancap.framework.api.locale.Locales;

public class LAPI {

    private static Locales locales;
    private static LanguageSettings settings;

    /**
     * It sets up the LAPI
     *
     * @param locales The Locales object that you created in the previous step.
     * @param settings The LanguageSettings object that you created in the previous step.
     */
    public static void setup(Locales locales, LanguageSettings settings) {
        LAPI.locales = locales;
        LAPI.settings = settings;
    }

    /**
     * It loads a locale into the base
     *
     * @param id The id of the message.
     * @param localized The localized string.
     * @param language The language to load the locale for.
     */
    public static void loadLocale(String id, String localized, Language language) {
        locales.loadLocale(id, localized, language);
    }

    /**
     * It returns the localized string for the given id, using the language of the player with the given playerID
     *
     * @param id The id of the string you want to localize.
     * @param playerID The player's ID.
     * @return The localized string.
     */
    public static String localized(String id, String playerID) {
        return locales.localized(id, settings.getLanguage(playerID));
    }

    public static void setupLanguage(String playerID, Language language) {
        settings.setLanguage(playerID, language);
    }

}
