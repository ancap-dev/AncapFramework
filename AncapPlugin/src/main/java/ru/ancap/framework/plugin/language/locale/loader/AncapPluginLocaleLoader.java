package ru.ancap.framework.plugin.language.locale.loader;

import lombok.AllArgsConstructor;
import org.bukkit.configuration.file.YamlConfiguration;
import ru.ancap.framework.api.loader.YamlLocaleLoader;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

@AllArgsConstructor
public class AncapPluginLocaleLoader {

    private final File folder;

    public void load() {
        for (InputStream stream : new LocaleStreamExtractor(folder).extract()) {
            new YamlLocaleLoader(
                    YamlConfiguration.loadConfiguration(new InputStreamReader(stream))
            ).load();
        }
    }
}
