package ru.ancap.framework.api.database;

import ru.ancap.framework.api.reader.DatabaseType;

public record DatabaseConnectionProperty(String domain, String driverClass, DatabaseType type) {

    public DatabaseDriverProperty drivers() {
        return new DatabaseDriverProperty(domain, driverClass);
    }

}
