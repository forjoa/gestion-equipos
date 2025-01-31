package org.example.components;

import org.example.lib.TeamDAO;
import org.example.models.Team;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteTeamWindow {
    private TeamDAO teamDAO = new TeamDAO();

    public DeleteTeamWindow() throws SQLException, IOException {
        JFrame frame = new JFrame("Selecciona el equipo a eliminar");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // combo box title
        JLabel titleLabel = new JLabel("Selecciona el equipo a eliminar");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        frame.add(titleLabel, BorderLayout.NORTH);

        // principal panel
        JPanel selectPanel = new JPanel(new GridBagLayout());
        selectPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        // team name
        JLabel nameLabel = new JLabel("Selecciona el equipo que deseas eliminar");
        JComboBox<Team> names = new JComboBox<>();
        for (Team team : teamDAO.getAllTeams()) {
            names.addItem(team);
        }
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        selectPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.ipady = 5;
        selectPanel.add(names, gbc);

        // send button
        JButton createButton = new JButton("Eliminar");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // 2 columns
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.ipady = 10;
        selectPanel.add(createButton, gbc);

        createButton.addActionListener(e -> {
            Team selectedTeam = (Team) names.getSelectedItem();
            assert selectedTeam != null;
            boolean response = teamDAO.deleteTeam(selectedTeam.getId());
            JOptionPane.showMessageDialog(frame, response ? "Equipo eliminado de manera correcta" : "Ocurrió un error");
        });

        // styling components
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        createButton.setBackground(Color.decode("#016BFF"));
        createButton.setForeground(Color.WHITE);
        createButton.setFont(new Font("Arial", Font.BOLD, 14));
        createButton.setFocusPainted(false);

        // add panel to frame
        frame.add(selectPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
