package ru.ancap.framework.plugin.api.configuration.extended.entensions.api;

import ru.ancap.framework.plugin.api.configuration.extended.entensions.exceptions.InvalidConfigurationMapException;

import java.util.Map;

public interface MapConfigurationSection {

    Map<String, String> getMap() throws InvalidConfigurationMapException;
}
