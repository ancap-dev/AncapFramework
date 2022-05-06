package ru.ancap.framework.plugin.api.plugins.info;

import ru.ancap.framework.plugin.api.configuration.AncapConfiguration;
import ru.ancap.framework.plugin.api.configuration.language.Language;
import ru.ancap.framework.plugin.api.configuration.language.PreAncapLanguage;
import ru.ancap.misc.strings.hexable.AncapIntegerString;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AncapPluginSettings extends AncapConfiguration {

    protected static class Path {
        public static final String ALTERNATE_LANGUAGES = "alternate_languages";
        public static final String MAIN_LANGUAGE = "main_language";
        public static final String PLUGIN_ID = "plugin_id";
    }

    public AncapPluginSettings(AncapConfiguration configuration) {
        super(configuration);
    }

    public int getPluginId() {
        String string = this.getString(Path.PLUGIN_ID);
        return new AncapIntegerString(string).getValue();
    }

    public Set<Language> getLanguages() {
        Set<Language> languages = this.getAlternateLanguages();
        languages.add(this.getMainLanguage());
        return languages;
    }

    public Set<Language> getAlternateLanguages() {
        List<String> strings = this.getStringList(Path.ALTERNATE_LANGUAGES);
        Set<Language> languages = new HashSet<>();
        for (String string : strings) {
            languages.add(new PreAncapLanguage(string).getPrepared());
        }
        return languages;
    }

    public Language getMainLanguage() {
        return new PreAncapLanguage(
                this.getString(Path.MAIN_LANGUAGE)
        ).getPrepared();
    }
}
