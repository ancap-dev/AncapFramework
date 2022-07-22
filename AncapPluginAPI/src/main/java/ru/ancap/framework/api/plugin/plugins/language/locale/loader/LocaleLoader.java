package ru.ancap.framework.api.plugin.plugins.language.locale.loader;

import lombok.AllArgsConstructor;
import ru.ancap.framework.api.loader.YamlLocaleLoader;
import ru.ancap.framework.api.plugin.plugins.ResourceSource;
import ru.ancap.framework.api.plugin.plugins.config.StreamConfig;

import java.io.InputStream;
import java.util.logging.Logger;

@AllArgsConstructor
public class LocaleLoader implements Runnable{

    private final Logger logger;
    private final ResourceSource source;

    @Override
    public void run() {
        int index = 0;
        while (true) {
            InputStream stream = source.getResource("locale_"+index+".yml");
            if (stream == null) {
                break;
            }
            new YamlLocaleLoader(
                    new StreamConfig(
                            stream
                    )
            ).run();
            logger.info("Loaded #"+index+" locale");
            index++;
        }
    }
}
