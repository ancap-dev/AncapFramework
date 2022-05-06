package ru.ancap.framework.plugin.api.configuration.builder;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import ru.ancap.framework.plugin.api.configuration.AncapConfiguration;
import ru.ancap.framework.plugin.api.configuration.PreAncapConfiguration;
import ru.ancap.framework.plugin.api.plugins.ResourceSource;
import ru.ancap.framework.plugin.api.configuration.language.AncapMultiLanguageConfiguration;
import ru.ancap.framework.plugin.api.configuration.language.AncapMultiLanguageConfigurationImpl;
import ru.ancap.framework.plugin.api.configuration.language.Language;

import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AncapMultiLanguageConfigurationBuilderImpl implements AncapMultiLanguageConfigurationBuilder {

    private File filesFolder;
    private ResourceSource resourceSource;

    private Language defaultLanguage;
    private Set<Language> languages;
    private Map<Language, AncapConfiguration> loadedConfigurations;

    private String configName = "config";

    protected Set<Language> getLanguageSet() {
        return languages;
    }
    protected File getFilesFolder() {
        return filesFolder;
    }
    protected ResourceSource getResourceSource() {
        return this.resourceSource;
    }
    protected String getConfigName() {
        return this.configName;
    }
    protected Language getDefaultLanguage() {
        return this.defaultLanguage;
    }

    public AncapMultiLanguageConfigurationBuilderImpl(File filesFolder, ResourceSource source, Language defaultLanguage) {
        this.languages = new HashSet<>();
        this.loadedConfigurations = new HashMap<>();
        this.filesFolder = filesFolder;
        this.resourceSource = source;
        this.defaultLanguage = defaultLanguage;
    }

    public AncapMultiLanguageConfigurationBuilderImpl(AncapMultiLanguageConfigurationBuilderImpl builder) {
        this(builder.getFilesFolder(), builder.getResourceSource(), builder.getDefaultLanguage());
    }

    @Override
    public AncapMultiLanguageConfigurationBuilder setLanguagesSet(Set<Language> languages) {
        Set<Language> languageSet = new HashSet<>(languages);
        languages.add(this.defaultLanguage);
        this.languages = languageSet;
        return this;
    }

    @Override
    public AncapMultiLanguageConfigurationBuilder addLanguage(Language language) {
        this.languages.add(language);
        return this;
    }

    @Override
    public AncapMultiLanguageConfigurationBuilder setConfigName(String string) {
        this.configName = string;
        return this;
    }

    @Override
    public AncapMultiLanguageConfiguration build() {
        this.loadFilesForLanguages(this.getResourceSource());
        return new AncapMultiLanguageConfigurationImpl(
                this.loadedConfigurations,
                this.getDefaultLanguage());
    }

    private void loadFilesForLanguages(ResourceSource source) {
        for (Language language : this.getLanguageSet()) {
            this.loadConfigFile(language, source);
        }
    }

    private void loadConfigFile(Language language, ResourceSource source) {
        String fileName = this.getConfigName()+"_"+language.getName()+".yml";
        Reader reader = new InputStreamReader(source.getResource(fileName));
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(reader);
        AncapConfiguration ancapConfiguration = new PreAncapConfiguration(configuration).getPrepared();
        this.loadedConfigurations.put(language, ancapConfiguration);
    }
}
