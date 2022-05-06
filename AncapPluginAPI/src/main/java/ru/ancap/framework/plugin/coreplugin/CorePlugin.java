package ru.ancap.framework.plugin.coreplugin;

import ru.ancap.framework.plugin.api.plugins.AncapPlugin;

public abstract class CorePlugin extends AncapPlugin {
    public abstract CoreMultiLanguageConfiguration getCoreMessageConfiguration();

}
