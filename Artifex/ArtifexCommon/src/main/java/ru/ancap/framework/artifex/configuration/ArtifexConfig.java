package ru.ancap.framework.artifex.configuration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.bukkit.configuration.ConfigurationSection;
import ru.ancap.commons.time.Day;
import ru.ancap.framework.language.language.Language;

import java.util.Calendar;
import java.util.TimeZone;

@Accessors(fluent = true) @Getter
@RequiredArgsConstructor
@SuppressWarnings("ClassCanBeRecord")
public class ArtifexConfig {

    private static ArtifexConfig loaded;

    private final ConfigurationSection section;

    public void load() {
        loaded = this;
    }

    public static ArtifexConfig loaded() {
        return loaded;
    }

    public Language defaultLanguage() {
        return Language.of(this.section.getString("language.server-native"));
    }

    public String databaseDataAccessKey() {
        return this.section.getString("database.connection.type").toLowerCase();
    }

    public ConfigurationSection databaseDriverDataSection() {
        return this.section.getConfigurationSection("database.data");
    }

    public ConfigurationSection databaseConnectionSection() {
        return this.section.getConfigurationSection("database.connection");
    }

    public long dayTimerAbsolute() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
        calendar.set(Calendar.HOUR_OF_DAY, this.section.getInt("everyday-timer.hour"));
        calendar.set(Calendar.MINUTE, this.section.getInt("everyday-timer.minute", 0));
        calendar.set(Calendar.SECOND, this.section.getInt("everyday-timer.second", 0));
        calendar.set(Calendar.MILLISECOND, this.section.getInt("everyday-timer.millisecond", 0));
        long millis = calendar.getTimeInMillis();
        if (millis < System.currentTimeMillis()) millis = millis + Day.MILLISECONDS;
        return millis;
    }
    
}
