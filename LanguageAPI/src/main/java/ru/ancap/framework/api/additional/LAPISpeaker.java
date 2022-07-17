package ru.ancap.framework.api.additional;

import lombok.AllArgsConstructor;
import ru.ancap.framework.api.LAPI;
import ru.ancap.framework.api.language.Language;

@AllArgsConstructor
public class LAPISpeaker implements Languager {

    private final String playerID;

    @Override
    public String localized(String id) {
        return LAPI.localized(id, playerID);
    }

    @Override
    public void setupLanguage(Language language) {
        LAPI.setupLanguage(playerID, language);
    }
}
