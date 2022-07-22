package ru.ancap.framework.api.database;

import java.sql.Connection;
import java.util.function.Supplier;

public interface ConnectionProvider extends Supplier<Connection> {

}
