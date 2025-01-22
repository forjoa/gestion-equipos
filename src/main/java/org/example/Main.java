package org.example;

import org.example.components.CreateTeamWindow;
import org.example.models.Database;

import javax.swing.*;
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

        JFrame frame = new JFrame("Gestión de Equipos y Jugadores");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        JMenuBar menuBar = new JMenuBar();

        JMenu teamMenu = new JMenu("Equipos");
        JMenuItem createTeamItem = new JMenuItem("Crear Equipo");
        JMenuItem listTeamsItem = new JMenuItem("Lista de Equipos");
        JMenuItem updateTeamItem = new JMenuItem("Actualizar Equipo");
        JMenuItem deleteTeamItem = new JMenuItem("Eliminar Equipo");

        teamMenu.add(createTeamItem);
        teamMenu.add(listTeamsItem);
        teamMenu.add(updateTeamItem);
        teamMenu.add(deleteTeamItem);

        menuBar.add(teamMenu);

        // actions for each window
        createTeamItem.addActionListener(e -> new CreateTeamWindow());
        // listTeamsItem.addActionListener(e -> new ListTeamsWindow());
        // updateTeamItem.addActionListener(e -> new UpdateTeamWindow());
        // deleteTeamItem.addActionListener(e -> new DeleteTeamWindow());

        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }
}