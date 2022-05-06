package ru.ancap.framework.plugin.coreplugin;

public class AncapCore implements Ancap {

    private final CorePlugin corePlugin;

    public AncapCore(CorePlugin plugin) {
        this.corePlugin = plugin;
    }

    public CorePlugin getCorePlugin() {
        return corePlugin;
    }

}
