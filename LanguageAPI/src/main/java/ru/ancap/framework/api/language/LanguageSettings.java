package ru.ancap.framework.api.language;

import org.jetbrains.annotations.NotNull;

public interface LanguageSettings {

    /**
     * Returns language, that setted player with inserted playerID or default.
     */
    Language getLanguage(@NotNull String playerName);

    /**
     * Set's the language for player.
     * @param playerName
     * @param language
     */
    void setLanguage(@NotNull String playerName, @NotNull Language language);
}
