package ru.ancap.framework.api.reader;

import java.sql.Connection;

public record InitializationContext(Connection connection) {

}
