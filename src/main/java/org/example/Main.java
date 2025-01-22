package org.example;

import org.example.lib.ModernTheme;
import org.example.models.Database;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        ModernTheme.apply();

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

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        JLabel label = new JLabel("testing");
        JButton button = new JButton("button test");
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        panel.add(label, gbc);
        panel.add(button, gbc);

        frame.add(panel);
        frame.setVisible(true);

        SwingUtilities.updateComponentTreeUI(frame);
    }
}