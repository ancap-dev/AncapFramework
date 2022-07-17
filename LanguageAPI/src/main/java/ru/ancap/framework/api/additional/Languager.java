package ru.ancap.framework.api.additional;

import ru.ancap.framework.api.language.Language;

public interface Languager {

    String localized(String id);
    void setupLanguage(Language language);

}
