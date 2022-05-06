package ru.ancap.framework.plugin.coreplugin;

import ru.ancap.framework.plugin.api.configuration.language.Language;

public interface CoreMultiLanguageConfiguration {

    CoreConfiguration getLanguage(Language language);

}
