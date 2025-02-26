package org.example.view;

import javax.swing.*;
import java.awt.*;

public class StopButton extends JButton {
    public StopButton() {
        super("Detener");
        setFont(new Font("Dialog", Font.BOLD, 14));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }
}