package org.example.view;

import javax.swing.*;
import java.awt.*;

public class PlayButton extends JButton {
    public PlayButton() {
        super("Iniciar");
        setFont(new Font("Dialog", Font.BOLD, 14));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}