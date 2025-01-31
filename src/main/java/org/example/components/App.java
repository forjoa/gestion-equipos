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

        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }
}
