package ru.ancap.framework.api.database;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import ru.ancap.framework.api.function.Holder;
import ru.ancap.framework.api.function.InitializedHolder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@AllArgsConstructor
public class RemoteConnectionProvider implements ConnectionProvider {

    private final Holder<Connection> holder = new InitializedHolder<>();
    private final Domain domain;
    private final Host host;
    private final Port port;
    private final DatabaseName databaseName;
    private final User user;
    private final Password password;

    @Override
    @SneakyThrows
    public Connection get() {
        return holder.get(() -> {
            try {
                return DriverManager.getConnection(
                        "jdbc:"+domain.domain+"://"+host.host+":"+port.port+"/"+databaseName.name+"?useUnicode=true&characterEncoding=utf8&autoReconnect=true",
                        user.name,
                        password.password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public record Domain(String domain) {}
    public record Host(String host) {}
    public record Port(int port) {}
    public record DatabaseName(String name) {}
    public record User(String name) {}
    public record Password(String password) {}
}
