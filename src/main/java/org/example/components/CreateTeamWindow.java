package org.example.components;

import javax.swing.*;
import java.awt.*;

public class CreateTeamWindow {
    public CreateTeamWindow() {
        JFrame frame = new JFrame("Crear Equipo");
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // Título del formulario
        JLabel titleLabel = new JLabel("Crear un Nuevo Equipo");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Panel principal con GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Espaciado entre componentes
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0; // Permite que los inputs se expandan horizontalmente

        // Nombre del equipo
        JLabel nameLabel = new JLabel("Nombre del equipo:");
        JTextField nameField = new JTextField();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0; // Las etiquetas no se expanden
        formPanel.add(nameLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0; // Los campos de texto sí se expanden
        formPanel.add(nameField, gbc);

        // Nombre de la ciudad
        JLabel cityLabel = new JLabel("Nombre de la ciudad:");
        JTextField cityField = new JTextField();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0; // Etiquetas no se expanden
        formPanel.add(cityLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0; // Campos de texto se expanden
        formPanel.add(cityField, gbc);

        // Nombre del estadio
        JLabel stadiumLabel = new JLabel("Nombre del estadio:");
        JTextField stadiumField = new JTextField();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0; // Etiquetas no se expanden
        formPanel.add(stadiumLabel, gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0; // Campos de texto se expanden
        formPanel.add(stadiumField, gbc);

        // Botón de creación
        JButton createButton = new JButton("Crear");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Abarca dos columnas
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(createButton, gbc);

        // Estilizar componentes con CustomUI
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        cityLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        stadiumLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        createButton.setBackground(Color.decode("#016BFF"));
        createButton.setForeground(Color.WHITE);
        createButton.setFont(new Font("Arial", Font.BOLD, 14));
        createButton.setFocusPainted(false);

        // Agregar el panel al frame
        frame.add(formPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
