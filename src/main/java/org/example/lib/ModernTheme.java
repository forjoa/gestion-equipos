package org.example.lib;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class ModernTheme {
    // color palette
    private static final Color PRIMARY = new Color(79, 70, 229);
    private static final Color SECONDARY = new Color(255, 255, 255);
    private static final Color BACKGROUND = new Color(243, 244, 246);
    private static final Color TEXT = new Color(31, 41, 55);

    public static void apply() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

            // general settings
            UIManager.put("Panel.background", new ColorUIResource(BACKGROUND));
            UIManager.put("TextField.background", new ColorUIResource(SECONDARY));
            UIManager.put("TextField.border", BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(PRIMARY.brighter(), 1),
                    BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));

            // customize buttons
            UIManager.put("Button.ui", new BasicButtonUI() {
                @Override
                public void installUI(JComponent c) {
                    super.installUI(c);
                    AbstractButton button = (AbstractButton) c;
                    button.setOpaque(false);
                    button.setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
                    button.setBackground(PRIMARY);
                    button.setForeground(SECONDARY);
                }

                @Override
                public void paint(Graphics g, JComponent c) {
                    AbstractButton b = (AbstractButton) c;
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                            RenderingHints.VALUE_ANTIALIAS_ON);

                    // draw rounded background
                    if (b.getModel().isPressed()) {
                        g2.setColor(PRIMARY.darker());
                    } else if (b.getModel().isRollover()) {
                        g2.setColor(PRIMARY.brighter());
                    } else {
                        g2.setColor(PRIMARY);
                    }

                    RoundRectangle2D.Float r = new RoundRectangle2D.Float(0, 0,
                            c.getWidth() - 1, c.getHeight() - 1, 10, 10);
                    g2.fill(r);

                    // draw text
                    super.paint(g2, c);
                    g2.dispose();
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
