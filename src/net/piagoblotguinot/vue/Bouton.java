package net.piagoblotguinot.vue;

import javax.swing.*;
import java.awt.*;

public class Bouton extends JButton
{
    private Dimension dimension = new Dimension(170, 30);
    private Color colorbg = new Color(255,255,255);
    private Color colorfg = new Color(0, 0, 0);

    Bouton(String label)
    {
        this.setText(label);
        this.setSize(this.dimension);
        this.setBackground(this.colorbg);
        this.setForeground(this.colorfg);
    }
}