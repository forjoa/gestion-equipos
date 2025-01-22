package org.example.components;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;

public class App {
    public App() {
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
        createTeamItem.addActionListener(e -> {
            try {
                new CreateTeamWindow();
            } catch (SQLException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        listTeamsItem.addActionListener(e -> {
            try {
                new ListTeamsWindow();
            } catch (SQLException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        // updateTeamItem.addActionListener(e -> new UpdateTeamWindow());
        // deleteTeamItem.addActionListener(e -> new DeleteTeamWindow());

        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }
}
