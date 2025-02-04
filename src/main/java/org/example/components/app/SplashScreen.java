package org.example.components.app;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JDialog {
    private JProgressBar progressBar;

    public SplashScreen() {
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
            UIManager.put("Button.arc", 15);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setUndecorated(true);
        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel logoLabel = new JLabel("Gestión Equipos", JLabel.CENTER);
        logoLabel.setFont(new Font("Arial", Font.BOLD, 14));
        logoLabel.setForeground(Color.WHITE);

        JLabel messageLabel = new JLabel("Cargando recursos, por favor espere...", JLabel.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        messageLabel.setForeground(Color.GRAY);

        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setBorder(null);
        progressBar.setFont(new Font("Arial", Font.BOLD, 14));
        progressBar.setForeground(Color.decode("#016BFF"));

        mainPanel.add(logoLabel, BorderLayout.CENTER);
        mainPanel.add(messageLabel, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);
        add(progressBar, BorderLayout.SOUTH);
    }

    public void updateProgress(int progress) {
        progressBar.setValue(progress);
    }
}
