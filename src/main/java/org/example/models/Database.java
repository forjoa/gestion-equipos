package org.example.models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String CONNECTION_URL = "jdbc:postgresql://ep-delicate-surf-a8nr8vuo.eastus2.azure.neon.tech/neondb?user=neondb_owner&password=npg_pOAVsTgd96iQ&sslmode=require";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(CONNECTION_URL);
    }
}
