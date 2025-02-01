package org.example.components;

import org.example.lib.PlayerDAO;
import org.example.lib.TeamDAO;
import org.example.models.Player;
import org.example.models.Team;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class EditPlayerWindow {
    public PlayerDAO playerDAO = new PlayerDAO();
    public TeamDAO teamDAO = new TeamDAO();

    public EditPlayerWindow(Integer id) throws SQLException, IOException {
        Player currentPlayer = playerDAO.getPlayerById(id);

        JFrame frame = new JFrame("Editar Jugador");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // form title
        JLabel titleLabel = new JLabel("Crear un Nuevo Jugador");
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
        JLabel nameLabel = new JLabel("Nombre del jugador:");
        JTextField nameField = new JTextField(currentPlayer.getName());
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        formPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.ipady = 5;
        formPanel.add(nameField, gbc);

        // position name
        JLabel positionLabel = new JLabel("Posicion:");
        JTextField positionField = new JTextField(currentPlayer.getPosition());
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        formPanel.add(positionLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.ipady = 5;
        formPanel.add(positionField, gbc);

        // stadium name
        JLabel teamLabel = new JLabel("Nombre del Equipo:");
        JComboBox<Team> names = new JComboBox<>();
        for(Team team: teamDAO.getAllTeams()) {
            names.addItem(team);
        }
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        formPanel.add(teamLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.ipady = 5;
        formPanel.add(names, gbc);

        // send button
        JButton createButton = new JButton("Editar");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // 2 columns
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.ipady = 10;
        formPanel.add(createButton, gbc);

        // styling components
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        positionLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        teamLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        createButton.setBackground(Color.decode("#016BFF"));
        createButton.setForeground(Color.WHITE);
        createButton.setFont(new Font("Arial", Font.BOLD, 14));
        createButton.setFocusPainted(false);

        // action when sending form
        createButton.addActionListener(e -> {
            String playerName = nameField.getText();
            String positionName = positionField.getText();
            Team team = (Team) names.getSelectedItem();

            boolean response = playerDAO.updatePlayer(new Player(id, playerName, positionName, team.getId()));

            JOptionPane.showMessageDialog(frame, response ? "Equipo agregado de manera correcta" : "Ocurrió un error");

            frame.dispose();
        });

        // add panel to frame
        frame.add(formPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
