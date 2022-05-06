package ru.ancap.framework.plugin.api.configuration.language;

import ru.ancap.framework.plugin.api.configuration.AncapConfiguration;

import java.util.Map;

public class AncapMultiLanguageConfigurationImpl extends AncapMultiLanguageConfiguration {

    public AncapMultiLanguageConfigurationImpl(Map<Language, AncapConfiguration> configurationsMap, Language defaultLanguage) {
        super(configurationsMap, defaultLanguage);
    }
}
