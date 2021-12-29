package net.piagoblotguinot.vue;

import net.piagoblotguinot.controleur.Controleur;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class PanneauUvs extends JPanel
{
    private Controleur controleur;
    private Point emplacement;

    public PanneauUvs(Controleur controleur, Point emplacement)
    {
        this.controleur = controleur;
        this.emplacement = emplacement;
        initialiser();
    }

    private void initialiser()
    {
        this.setLayout(new FlowLayout());
        this.setBounds(emplacement.x,emplacement.y,480,263);
        this.setBackground(Color.DARK_GRAY);

        for(int i = 1; i <13;i++)
        {
            try {
                BufferedImage original = ImageIO.read(new File("data/Carte_" + i + ".png"));
                Image image = original.getScaledInstance(114/*(1024/9)*/, 82/*(734/9)*/, Image.SCALE_DEFAULT);
                ImageIcon icone = new ImageIcon(image);
                JLabel label = new JLabel(icone);
                this.add(label);
            } catch (Exception e) {
            }
        }
    }
}
