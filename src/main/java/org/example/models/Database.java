package org.example.models;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Class to access Database
 * @author Joaquin Trujillo
 */
public class Database {
    static Properties properties = new Properties();

    /**
     * Function to get the database connection URL from the application.properties file
     * @return the connection as string
     * @throws IOException exception
     */
    public static String getDatabaseUrl() throws IOException {
        properties.load(Database.class.getClassLoader().getResourceAsStream("application.properties"));
        return properties.getProperty("db.url");
    }

    /**
     * Function to access the connection to start queries
     * @return Connection object
     * @throws SQLException exception
     * @throws IOException exception
     */
    public static Connection getConnection() throws SQLException, IOException {
        return DriverManager.getConnection(getDatabaseUrl());
    }
}
