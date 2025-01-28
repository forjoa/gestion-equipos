package org.example.components;

import org.example.lib.TeamDAO;
import org.example.models.Team;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class CreateTeamWindow {
    private TeamDAO teamDAO = new TeamDAO();

    public CreateTeamWindow() throws SQLException, IOException {
        JFrame frame = new JFrame("Crear Equipo");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // form title
        JLabel titleLabel = new JLabel("Crear un Nuevo Equipo");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        frame.add(titleLabel, BorderLayout.NORTH);

        // principal panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // spacing between components
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0; // allows inputs to expand

        // team name
        JLabel nameLabel = new JLabel("Nombre del equipo:");
        JTextField nameField = new JTextField();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        formPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.ipady = 5;
        formPanel.add(nameField, gbc);

        // city name
        JLabel cityLabel = new JLabel("Nombre de la ciudad:");
        JTextField cityField = new JTextField();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        formPanel.add(cityLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.ipady = 5;
        formPanel.add(cityField, gbc);

        // stadium name
        JLabel stadiumLabel = new JLabel("Nombre del estadio:");
        JTextField stadiumField = new JTextField();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        formPanel.add(stadiumLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.ipady = 5;
        formPanel.add(stadiumField, gbc);

        // send button
        JButton createButton = new JButton("Crear");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // 2 columns
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.ipady = 10;
        formPanel.add(createButton, gbc);

        // styling components
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        cityLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        stadiumLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        createButton.setBackground(Color.decode("#016BFF"));
        createButton.setForeground(Color.WHITE);
        createButton.setFont(new Font("Arial", Font.BOLD, 14));
        createButton.setFocusPainted(false);

        // action when sending form
        createButton.addActionListener(e -> {
            String teamName = nameField.getText();
            String cityName = cityField.getText();
            String stadiumName = stadiumField.getText();

            boolean response = teamDAO.addTeam(new Team(0, teamName, cityName, stadiumName));

            JOptionPane.showMessageDialog(frame, response ? "Equipo agregado de manera correcta" : "Ocurrió un error");
        });

        // add panel to frame
        frame.add(formPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
