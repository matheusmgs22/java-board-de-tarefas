package br.com.dio.persistence.config;

import static lombok.AccessLevel.PRIVATE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class ConnectionConfig {

    public static Connection getConnection() throws SQLException {

        var url = "jdbc:mysql://localhost/board";
        var user = "board";
        var password = "board";

        var connection = DriverManager.getConnection(url, user, password);
        connection.setAutoCommit(false);

        return connection;
    }
}
