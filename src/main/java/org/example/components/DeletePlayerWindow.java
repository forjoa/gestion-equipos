package org.example.components;

import org.example.lib.PlayerDAO;
import org.example.models.Player;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class DeletePlayerWindow {
    private PlayerDAO playerDAO = new PlayerDAO();

    public DeletePlayerWindow() throws SQLException, IOException {
        JFrame frame = new JFrame("Selecciona el jugador a eliminar");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // combo box title
        JLabel titleLabel = new JLabel("Selecciona el jugador a eliminar");
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
        JLabel nameLabel = new JLabel("Selecciona el jugador que deseas eliminar");
        JComboBox<Player> names = new JComboBox<>();
        for (Player player : playerDAO.getAllPlayers()) {
            names.addItem(player);
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
            Player selectedTeam = (Player) names.getSelectedItem();
            assert selectedTeam != null;
            boolean response = playerDAO.deletePlayer(selectedTeam.getId());
            JOptionPane.showMessageDialog(frame, response ? "Jugador eliminado de manera correcta" : "Ocurrió un error");
            frame.dispose();
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
