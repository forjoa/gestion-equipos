package org.example.components;

import javax.swing.*;
import java.awt.*;

public class CreateTeamWindow {
    public CreateTeamWindow() {
        JFrame frame = new JFrame("Crear Equipo");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Crear un Nuevo Equipo");
        CustomUI.styleLabel(titleLabel);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        frame.add(titleLabel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel nameLabel = new JLabel("Nombre del Equipo:");
        CustomUI.styleLabel(nameLabel);
        JTextField nameField = new JTextField();

        JButton createButton = new JButton("Crear");
        CustomUI.styleButton(createButton);

        formPanel.add(nameLabel);
        formPanel.add(nameField);
        formPanel.add(new JLabel()); // Espacio vacío
        formPanel.add(createButton);

        frame.add(formPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}

