package ru.ancap.framework.plugin.api.plugins;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.nio.file.Files;

public class AncapPluginResourceSource implements ResourceSource {

    private JavaPlugin plugin;
    private boolean saveFiles;

    public AncapPluginResourceSource(JavaPlugin plugin, boolean saveFiles) {
        this.plugin = plugin;
        this.saveFiles = saveFiles;
    }

    protected JavaPlugin getPlugin() {
        return this.plugin;
    }

    protected boolean isSaveFiles() {
        return this.saveFiles;
    }

    @Override
    public InputStream getResource(String fileName) {
        File file = new File(plugin.getDataFolder(), fileName);
        if (file.exists()) {
            try {
                return new FileInputStream(file);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        InputStream stream = plugin.getResource(fileName);
        this.prepareFiles(file, stream);
        return plugin.getResource(fileName);
    }

    private void prepareFiles(File file, InputStream stream) {
        if (saveFiles) {
            this.createFile(file, stream);
        }
    }

    private void createFile(File file, InputStream stream) {
        try (stream) {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            Files.copy(stream, file.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
