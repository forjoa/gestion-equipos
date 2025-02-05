package org.example.components.team;

import org.example.lib.TeamDAO;
import org.example.lib.TeamTableConverter;
import org.example.models.Team;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Window to list all teams and their respective information except their list of players
 * @author Joaquin Trujillo
 */
public class ListTeamsWindow {
    private TeamDAO teamDAO = new TeamDAO();

    public ListTeamsWindow() throws SQLException, IOException {
        JFrame frame = new JFrame("Listar Equipo");
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // table title
        JLabel titleLabel = new JLabel("Listado de equipos");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        frame.add(titleLabel, BorderLayout.NORTH);

        // principal panel
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        // setting table
        String[] columnNames = {"ID", "Nombre", "Ciudad", "Estadio"};
        List<Team> teams = teamDAO.getAllTeams();
        Object[][] data = TeamTableConverter.convertListToArray(teams);

        JTable table = new JTable(data, columnNames);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        table.getTableHeader().setReorderingAllowed(false);

        // scroll pane for table
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // add scroll pane to main panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(scrollPane, gbc);

        // styling components
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(Color.decode("#016BFF"));
        table.getTableHeader().setForeground(Color.WHITE);

        // add panel to frame
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
