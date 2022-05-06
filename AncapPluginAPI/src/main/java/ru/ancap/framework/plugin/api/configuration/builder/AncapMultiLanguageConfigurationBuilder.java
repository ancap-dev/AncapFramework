package ru.ancap.framework.plugin.api.configuration.builder;

import ru.ancap.framework.plugin.api.configuration.language.AncapMultiLanguageConfiguration;
import ru.ancap.framework.plugin.api.configuration.language.Language;

import java.util.Set;

public interface AncapMultiLanguageConfigurationBuilder {

    AncapMultiLanguageConfigurationBuilder setLanguagesSet(Set<Language> languages);
    AncapMultiLanguageConfigurationBuilder addLanguage(Language language);
    AncapMultiLanguageConfigurationBuilder setConfigName(String name);

    AncapMultiLanguageConfiguration build();
}
