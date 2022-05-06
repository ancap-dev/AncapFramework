package ru.ancap.framework.plugin.api.configuration.language;

import ru.ancap.framework.plugin.api.configuration.AncapConfiguration;

import java.util.Map;
import java.util.NoSuchElementException;

public abstract class AncapMultiLanguageConfiguration {

    private Language defaultLanguage;
    private Map<Language, AncapConfiguration> configurationsMap;

    public AncapMultiLanguageConfiguration(Map<Language, AncapConfiguration> configurationsMap, Language defaultLanguage) {
        this.configurationsMap = configurationsMap;
        this.defaultLanguage = defaultLanguage;
    }

    public AncapMultiLanguageConfiguration(AncapMultiLanguageConfiguration configuration) {
        this(configuration.getConfigurationsMap(), configuration.getDefaultLanguage());
    }

    protected Map<Language, AncapConfiguration> getConfigurationsMap() {
        return this.configurationsMap;
    }

    protected Language getDefaultLanguage() {
        return this.defaultLanguage;
    }

    public AncapConfiguration getLanguage(Language language) {
        AncapConfiguration configuration = this.configurationsMap.get(language);
        if (configuration == null) {
            configuration = this.configurationsMap.get(this.getDefaultLanguage());
        }
        if (configuration == null) {
            throw new NoSuchElementException("Invalid MultiLanguageConfiguration usage: neither "+language.getName()+" language, nor default language ("+this.getDefaultLanguage().getName()+") was found!");
        }
        return configuration;
    }
}
