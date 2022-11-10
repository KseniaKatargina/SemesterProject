package ru.kpfu.itis.util;

import ru.kpfu.itis.exceptions.DBException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionProvider {
    private static ConnectionProvider instance;


    public static ConnectionProvider getInstance() throws DBException {
        if (instance == null) {
            instance = new ConnectionProvider();
        }
        return instance;
    }

    private Connection connection;

    private ConnectionProvider() throws DBException {
        try {
            Properties properties = new Properties();
            properties.load(ConnectionProvider.class.getResourceAsStream("/db.properties"));

            String dbDriver = properties.getProperty("db.driver");
            String dbUrl = properties.getProperty("db.url");
            String dbUsername = properties.getProperty("db.username");
            String dbPassword = properties.getProperty("db.password");

            Class.forName(dbDriver);
            connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        } catch (SQLException | ClassNotFoundException | IOException e) {
            throw new DBException("Can't connect to DB", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
