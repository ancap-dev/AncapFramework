package ru.ancap.framework.api.database;

import lombok.AllArgsConstructor;
import ru.ancap.framework.api.function.Holder;
import ru.ancap.framework.api.function.InitializedHolder;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@AllArgsConstructor
public class LocalConnectionProvider implements ConnectionProvider {

    private final Holder<Connection> connectionHolder = new InitializedHolder<>();
    private final String domain;
    private final File file;

    @Override
    public Connection get() {
        return connectionHolder.get(() -> {
            try {
                return DriverManager.getConnection("jdbc:"+domain+":"+file.getPath());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
