package org.example.view;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel {
    private PlayButton playButton;
    private StopButton stopButton;

    // Colores para los botones
    private static final Color PLAY_COLOR = new Color(50, 180, 50);
    private static final Color STOP_COLOR = new Color(180, 50, 50);
    private static final Color BUTTON_TEXT = Color.WHITE;

    public ControlPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        setBackground(Color.WHITE);
        initButtons();
    }

    private void initButtons() {
        // Botón Play con estilo
        playButton = new PlayButton();
        styleButton(playButton, PLAY_COLOR, "Iniciar Simulación");
        playButton.setIcon(createIcon('\u25B6', BUTTON_TEXT, playButton.getFont()));

        // Botón Stop con estilo
        stopButton = new StopButton();
        styleButton(stopButton, STOP_COLOR, "Detener Simulación");
        stopButton.setIcon(createIcon('\u25A0', BUTTON_TEXT, stopButton.getFont()));

        add(playButton);
        add(stopButton);
    }

    private void styleButton(JButton button, Color bgColor, String toolTip) {
        button.setFont(new Font("Dialog", Font.BOLD, 14));
        button.setForeground(BUTTON_TEXT);
        button.setBackground(bgColor);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setToolTipText(toolTip);

        // Dimensiones del botón
        button.setPreferredSize(new Dimension(150, 40));

        // Efecto hover
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(bgColor);
            }
        });
    }

    private Icon createIcon(char unicode, Color color, Font baseFont) {
        final String text = Character.toString(unicode);
        Font iconFont = baseFont.deriveFont(Font.BOLD, 16f);

        return new Icon() {
            public void paintIcon(Component c, Graphics g, int x, int y) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setFont(iconFont);
                g2.setColor(color);
                FontMetrics fm = g2.getFontMetrics();
                g2.drawString(text, x, y + fm.getAscent());
                g2.dispose();
            }

            public int getIconWidth() {
                return 16;
            }

            public int getIconHeight() {
                return 16;
            }
        };
    }

    public PlayButton getPlayButton() { return playButton; }
    public StopButton getStopButton() { return stopButton; }
}