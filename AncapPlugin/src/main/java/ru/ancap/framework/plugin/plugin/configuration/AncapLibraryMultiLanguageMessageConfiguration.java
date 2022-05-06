package ru.ancap.framework.plugin.plugin.configuration;

import ru.ancap.framework.plugin.api.configuration.language.AncapMultiLanguageConfiguration;
import ru.ancap.framework.plugin.api.configuration.language.Language;
import ru.ancap.framework.plugin.coreplugin.CoreMultiLanguageConfiguration;

public class AncapLibraryMultiLanguageMessageConfiguration extends AncapMultiLanguageConfiguration implements CoreMultiLanguageConfiguration {

    public AncapLibraryMultiLanguageMessageConfiguration(AncapMultiLanguageConfiguration configuration) {
        super(configuration);
    }

    @Override
    public AncapLibraryMessageConfiguration getLanguage(Language language) {
        return new AncapLibraryMessageConfiguration(super.getLanguage(language));
    }
}
