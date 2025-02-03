package org.example.components.team;

import org.example.models.Team;
import org.example.models.Player;
import org.example.models.TeamPlayers;
import org.example.lib.TeamDAO;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class TeamDetailsWindow {
    private TeamDAO teamDAO = new TeamDAO();

    public TeamDetailsWindow(Integer teamId) throws SQLException, IOException {
        TeamPlayers teamPlayers = teamDAO.getAllTeamPlayersById(teamId);

        if (teamPlayers == null) {
            JOptionPane.showMessageDialog(null, "Equipo no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Team team = teamPlayers.getTeam();
        List<Player> playersList = teamPlayers.getPlayersList();

        JFrame frame = new JFrame("Detalles del Equipo");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Detalles del Equipo: " + team.getName());
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        frame.add(titleLabel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 30, 20));

        JPanel teamInfoPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        teamInfoPanel.setBorder(BorderFactory.createTitledBorder("Información del Equipo"));

        addLabelAndValue(teamInfoPanel, "Nombre:", team.getName());
        addLabelAndValue(teamInfoPanel, "Ciudad:", team.getCity());
        addLabelAndValue(teamInfoPanel, "Estadio:", team.getStadium());

        mainPanel.add(teamInfoPanel, BorderLayout.NORTH);

        JPanel playersPanel = new JPanel(new BorderLayout());
        playersPanel.setBorder(BorderFactory.createTitledBorder("Jugadores"));

        JScrollPane playersScrollPane = getJScrollPane(playersList);
        playersPanel.add(playersScrollPane, BorderLayout.CENTER);

        mainPanel.add(playersPanel, BorderLayout.CENTER);

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

    private static JScrollPane getJScrollPane(List<Player> playersList) {
        DefaultListModel<String> playersListModel = new DefaultListModel<>();
        for (Player player : playersList) {
            playersListModel.addElement("      " + player.getName() + " - " + player.getPosition());
        }

        JList<String> playersListComponent = new JList<>(playersListModel);
        playersListComponent.setFont(new Font("Arial", Font.PLAIN, 14));
        playersListComponent.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        return new JScrollPane(playersListComponent);
    }

    private void addLabelAndValue(JPanel panel, String labelText, String valueText) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel value = new JLabel(valueText);
        value.setFont(new Font("Arial", Font.PLAIN, 14));

        panel.add(label);
        panel.add(value);
    }
}