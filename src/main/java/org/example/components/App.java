package org.example.components;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;

public class App {
    public App() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
            UIManager.put("Button.arc", 15);
        } catch (Exception e) {
            e.printStackTrace();
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
        JMenuItem detailTeamItem = new JMenuItem("Detalles de Equipo");

        teamMenu.add(createTeamItem);
        teamMenu.add(listTeamsItem);
        teamMenu.add(updateTeamItem);
        teamMenu.add(deleteTeamItem);
        teamMenu.add(detailTeamItem);

        JMenu playerMenu = new JMenu("Jugadores");
        JMenuItem createPlayerItem = new JMenuItem("Crear Jugador");
        JMenuItem updatePlayerItem = new JMenuItem("Actualizar Jugador");
        JMenuItem deletePlayerItem = new JMenuItem("Eliminar Jugador");
        JMenuItem detailPlayerItem = new JMenuItem("Detalles del Jugador");

        playerMenu.add(createPlayerItem);
        playerMenu.add(updatePlayerItem);
        playerMenu.add(deletePlayerItem);
        playerMenu.add(detailPlayerItem);

        menuBar.add(teamMenu);
        menuBar.add(playerMenu);

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
        updateTeamItem.addActionListener(e -> {
            try {
                new SelectTeamEditWindow();
            } catch (SQLException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        deleteTeamItem.addActionListener(e -> {
            try {
                new DeleteTeamWindow();
            } catch (SQLException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        detailTeamItem.addActionListener(e -> {
            try {
                new SelectTeamDetailsWindow();
            } catch (SQLException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // actions for each player window
        createPlayerItem.addActionListener(e -> {
            try {
                new CreatePlayerWindow();
            } catch (SQLException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        updatePlayerItem.addActionListener(e -> {
            try {
                new SelectPlayerEditWindow();
            } catch (SQLException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        deletePlayerItem.addActionListener(e -> {
            try {
                new DeletePlayerWindow();
            } catch (SQLException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        detailPlayerItem.addActionListener(e -> {
            try {
                new SelectPlayerDetailsWindow();
            } catch (SQLException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }
}
