package ru.ancap.framework.api.loader;

import lombok.AllArgsConstructor;
import org.bukkit.configuration.ConfigurationSection;
import ru.ancap.framework.api.LAPI;
import ru.ancap.framework.api.loader.exception.LocaleLoaderException;
import ru.ancap.framework.api.language.Language;

import java.util.Set;

@AllArgsConstructor
public class YamlLocaleLoader {

    private final ConfigurationSection section;

    public void load() {
        String languageCode = this.section.getString("language");
        if (languageCode == null) {
            throw new LocaleLoaderException("Can't load locale without language code!");
        }
        Language language = Language.of(languageCode);
        Set<String> keySet = this.section.getKeys(true);
        for (String key : keySet) {
            String localizedMessage = section.getString(key);
            LAPI.loadLocale(key, localizedMessage, language);
        }
    }

}
