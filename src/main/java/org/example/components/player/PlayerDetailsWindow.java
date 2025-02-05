package org.example.components.player;

import org.example.lib.PlayerDAO;
import org.example.lib.TeamDAO;
import org.example.models.Player;
import org.example.models.Team;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Window to display the details of the player previously selected by his ID
 * @author Joaquin Trujillo
 */
public class PlayerDetailsWindow {
    private PlayerDAO playerDAO = new PlayerDAO();
    private TeamDAO teamDAO = new TeamDAO();

    public PlayerDetailsWindow(Integer id) throws SQLException, IOException {
        Player currentPlayer = playerDAO.getPlayerById(id);
        Team currentTeam = teamDAO.getTeamById(currentPlayer.getTeam_id());

        JFrame frame = new JFrame("Detalles del Jugador");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Detalles del jugador: " + currentPlayer.getName());
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        frame.add(titleLabel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 30, 20));

        JPanel teamInfoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        teamInfoPanel.setBorder(BorderFactory.createTitledBorder("Información del Jugador"));

        addLabelAndValue(teamInfoPanel, "Nombre:", currentPlayer.getName());
        addLabelAndValue(teamInfoPanel, "Posicion:", currentPlayer.getPosition());
        addLabelAndValue(teamInfoPanel, "Equipo:", currentTeam.getName());

        mainPanel.add(teamInfoPanel, BorderLayout.NORTH);

        JButton closeButton = new JButton("Cerrar");
        closeButton.setBackground(Color.decode("#FF4444"));
        closeButton.setForeground(Color.WHITE);
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> frame.dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        buttonPanel.add(closeButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    /**
     * Function to create a JLabel with its respective value
     * * @param panel frame where the JLabel will go
     * * @param labelText value for the text label
     * * @param valueText value for the text (player description)
     */
    private void addLabelAndValue(JPanel panel, String labelText, String valueText) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel value = new JLabel(valueText);
        value.setFont(new Font("Arial", Font.PLAIN, 14));

        panel.add(label);
        panel.add(value);
    }
}
