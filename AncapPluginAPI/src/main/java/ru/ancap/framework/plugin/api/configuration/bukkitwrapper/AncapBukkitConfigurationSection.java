package ru.ancap.framework.plugin.api.configuration.bukkitwrapper;

import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Class-wrapping for ConfigurationSection. I'd made it because no-one Bukkit implementations supported instancing by instance.
 * It might be bad code, but I don't know how to do it better
 *
 * When making an extensions for this class, please make the getString/getStringList the root method for data withdrawing from config.
 **/

public class AncapBukkitConfigurationSection implements ConfigurationSection {

    private ConfigurationSection configurationSection;

    public AncapBukkitConfigurationSection(ConfigurationSection configurationSection) {
        this.configurationSection = configurationSection;
    }

    public AncapBukkitConfigurationSection(AncapBukkitConfigurationSection configurationSection) {
        this.configurationSection = configurationSection.getConfigurationSection();
    }

    protected ConfigurationSection getConfigurationSection() {
        return this.configurationSection;
    }

    @NotNull
    @Override
    public Set<String> getKeys(boolean deep) {
        return this.configurationSection.getKeys(deep);
    }

    @NotNull
    @Override
    public Map<String, Object> getValues(boolean deep) {
        return this.configurationSection.getValues(deep);
    }

    @Override
    public boolean contains(@NotNull String path) {
        return this.configurationSection.contains(path);
    }

    @Override
    public boolean contains(@NotNull String path, boolean ignoreDefault) {
        return this.configurationSection.contains(path, ignoreDefault);
    }

    @Override
    public boolean isSet(@NotNull String path) {
        return this.configurationSection.isSet(path);
    }

    @Nullable
    @Override
    public String getCurrentPath() {
        return this.configurationSection.getCurrentPath();
    }

    @NotNull
    @Override
    public String getName() {
        return this.configurationSection.getName();
    }

    @Nullable
    @Override
    public Configuration getRoot() {
        return this.configurationSection.getRoot();
    }

    @Nullable
    @Override
    public ConfigurationSection getParent() {
        return this.configurationSection.getParent();
    }

    @Nullable
    @Override
    public Object get(@NotNull String path) {
        return this.configurationSection.get(path);
    }

    @Nullable
    @Override
    public Object get(@NotNull String path, @Nullable Object def) {
        return this.configurationSection.get(path, def);
    }

    @Override
    public void set(@NotNull String path, @Nullable Object value) {
        this.configurationSection.set(path, value);
    }

    @NotNull
    @Override
    public ConfigurationSection createSection(@NotNull String path) {
        return this.configurationSection.createSection(path);
    }

    @NotNull
    @Override
    public ConfigurationSection createSection(@NotNull String path, @NotNull Map<?, ?> map) {
        return this.configurationSection.createSection(path, map);
    }

    @Override
    public String getString(@NotNull String path) {
        String string = this.configurationSection.getString(path);
        if (string == null) {
            throw new NoSuchElementException();
        }
        return string;
    }

    @NotNull
    @Override
    public String getString(@NotNull String path, @Nullable String def) {
        String string = this.configurationSection.getString(path, def);
        if (string == null) {
            throw new NoSuchElementException();
        }
        return string;
    }

    @Override
    public boolean isString(@NotNull String path) {
        return this.configurationSection.isString(path);
    }

    @Override
    public int getInt(@NotNull String path) {
        return this.configurationSection.getInt(path);
    }

    @Override
    public int getInt(@NotNull String path, int def) {
        return this.configurationSection.getInt(path, def);
    }

    @Override
    public boolean isInt(@NotNull String path) {
        return this.configurationSection.isInt(path);
    }

    @Override
    public boolean getBoolean(@NotNull String path) {
        return this.configurationSection.getBoolean(path);
    }

    @Override
    public boolean getBoolean(@NotNull String path, boolean def) {
        return this.configurationSection.getBoolean(path);
    }

    @Override
    public boolean isBoolean(@NotNull String path) {
        return this.configurationSection.isBoolean(path);
    }

    @Override
    public double getDouble(@NotNull String path) {
        return this.configurationSection.getDouble(path);
    }

    @Override
    public double getDouble(@NotNull String path, double def) {
        return this.configurationSection.getDouble(path, def);
    }

    @Override
    public boolean isDouble(@NotNull String path) {
        return this.configurationSection.isDouble(path);
    }

    @Override
    public long getLong(@NotNull String path) {
        return this.configurationSection.getLong(path);
    }

    @Override
    public long getLong(@NotNull String path, long def) {
        return this.configurationSection.getLong(path, def);
    }

    @Override
    public boolean isLong(@NotNull String path) {
        return this.configurationSection.isLong(path);
    }

    @Nullable
    @Override
    public List<?> getList(@NotNull String path) {
        return this.configurationSection.getList(path);
    }

    @Nullable
    @Override
    public List<?> getList(@NotNull String path, @Nullable List<?> def) {
        return this.configurationSection.getList(path, def);
    }

    @Override
    public boolean isList(@NotNull String path) {
        return this.configurationSection.isList(path);
    }

    @NotNull
    @Override
    public List<String> getStringList(@NotNull String path) {
        return this.configurationSection.getStringList(path);
    }

    @NotNull
    @Override
    public List<Integer> getIntegerList(@NotNull String path) {
        return this.configurationSection.getIntegerList(path);
    }

    @NotNull
    @Override
    public List<Boolean> getBooleanList(@NotNull String path) {
        return this.configurationSection.getBooleanList(path);
    }

    @NotNull
    @Override
    public List<Double> getDoubleList(@NotNull String path) {
        return this.configurationSection.getDoubleList(path);
    }

    @NotNull
    @Override
    public List<Float> getFloatList(@NotNull String path) {
        return this.configurationSection.getFloatList(path);
    }

    @NotNull
    @Override
    public List<Long> getLongList(@NotNull String path) {
        return this.configurationSection.getLongList(path);
    }

    @NotNull
    @Override
    public List<Byte> getByteList(@NotNull String path) {
        return this.configurationSection.getByteList(path);
    }

    @NotNull
    @Override
    public List<Character> getCharacterList(@NotNull String path) {
        return this.configurationSection.getCharacterList(path);
    }

    @NotNull
    @Override
    public List<Short> getShortList(@NotNull String path) {
        return this.configurationSection.getShortList(path);
    }

    @NotNull
    @Override
    public List<Map<?, ?>> getMapList(@NotNull String path) {
        return this.configurationSection.getMapList(path);
    }

    @Nullable
    @Override
    public <T> T getObject(@NotNull String path, @NotNull Class<T> clazz) {
        return this.configurationSection.getObject(path, clazz);
    }

    @Nullable
    @Override
    public <T> T getObject(@NotNull String path, @NotNull Class<T> clazz, @Nullable T def) {
        return this.configurationSection.getObject(path, clazz, def);
    }

    @Nullable
    @Override
    public <T extends ConfigurationSerializable> T getSerializable(@NotNull String path, @NotNull Class<T> clazz) {
        return this.configurationSection.getSerializable(path, clazz);
    }

    @Nullable
    @Override
    public <T extends ConfigurationSerializable> T getSerializable(@NotNull String path, @NotNull Class<T> clazz, @Nullable T def) {
        return this.configurationSection.getSerializable(path, clazz, def);
    }

    @Nullable
    @Override
    public Vector getVector(@NotNull String path) {
        return this.configurationSection.getVector(path);
    }

    @Nullable
    @Override
    public Vector getVector(@NotNull String path, @Nullable Vector def) {
        return this.configurationSection.getVector(path, def);
    }

    @Override
    public boolean isVector(@NotNull String path) {
        return this.configurationSection.isVector(path);
    }

    @Nullable
    @Override
    public OfflinePlayer getOfflinePlayer(@NotNull String path) {
        return this.configurationSection.getOfflinePlayer(path);
    }

    @Nullable
    @Override
    public OfflinePlayer getOfflinePlayer(@NotNull String path, @Nullable OfflinePlayer def) {
        return this.configurationSection.getOfflinePlayer(path, def);
    }

    @Override
    public boolean isOfflinePlayer(@NotNull String path) {
        return this.configurationSection.isOfflinePlayer(path);
    }

    @Nullable
    @Override
    public ItemStack getItemStack(@NotNull String path) {
        return this.configurationSection.getItemStack(path);
    }

    @Nullable
    @Override
    public ItemStack getItemStack(@NotNull String path, @Nullable ItemStack def) {
        return this.configurationSection.getItemStack(path, def);
    }

    @Override
    public boolean isItemStack(@NotNull String path) {
        return this.configurationSection.isItemStack(path);
    }

    @Nullable
    @Override
    public Color getColor(@NotNull String path) {
        return this.configurationSection.getColor(path);
    }

    @Nullable
    @Override
    public Color getColor(@NotNull String path, @Nullable Color def) {
        return this.configurationSection.getColor(path, def);
    }

    @Override
    public boolean isColor(@NotNull String path) {
        return this.configurationSection.isColor(path);
    }

    @Nullable
    @Override
    public Location getLocation(@NotNull String path) {
        return this.configurationSection.getLocation(path);
    }

    @Nullable
    @Override
    public Location getLocation(@NotNull String path, @Nullable Location def) {
        return this.configurationSection.getLocation(path, def);
    }

    @Override
    public boolean isLocation(@NotNull String path) {
        return this.configurationSection.isLong(path);
    }

    @Nullable
    @Override
    public ConfigurationSection getConfigurationSection(@NotNull String path) {
        return this.configurationSection.getConfigurationSection(path);
    }

    @Override
    public boolean isConfigurationSection(@NotNull String path) {
        return this.configurationSection.isConfigurationSection(path);
    }

    @Nullable
    @Override
    public ConfigurationSection getDefaultSection() {
        return this.configurationSection.getDefaultSection();
    }

    @Override
    public void addDefault(@NotNull String path, @Nullable Object value) {
        this.configurationSection.addDefault(path, value);
    }
}
