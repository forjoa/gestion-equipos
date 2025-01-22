package org.example;

import org.example.components.App;
import org.example.models.Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        String sql = "SELECT * FROM jugadores";

        try (Connection connection = Database.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(sql);) {
            while(rs.next()) {
                System.out.println("player");
                System.out.println("id: " + rs.getInt(1));
                System.out.println("name: " + rs.getString(2));
                System.out.println("position: " + rs.getString(3));
                System.out.println("team id: " + rs.getInt(4));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        new App();
    }
}