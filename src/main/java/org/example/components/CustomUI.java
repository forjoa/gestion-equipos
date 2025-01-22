package org.example.components;

import javax.swing.*;
import java.awt.*;

public class CustomUI {
    public static final Color BLUE = Color.decode("#016BFF");
    public static final Color BLACK = Color.decode("#17181A");
    public static final Color WHITE = Color.decode("#FFFFFF");
    public static final Color RED = Color.decode("#FB4447");

    public static void styleButton(JButton button) {
        button.setBackground(BLUE);
        button.setForeground(WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(BLACK));
    }

    public static void styleLabel(JLabel label) {
        label.setForeground(BLACK);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
    }
}
