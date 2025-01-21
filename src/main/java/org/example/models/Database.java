package org.example.models;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Database {
    static Properties properties = new Properties();

    public static String getDatabaseUrl() throws IOException {
        properties.load(Database.class.getClassLoader().getResourceAsStream("application.properties"));
        return properties.getProperty("db.url");
    }

    public static Connection getConnection() throws SQLException, IOException {
        return DriverManager.getConnection(getDatabaseUrl());
    }
}
