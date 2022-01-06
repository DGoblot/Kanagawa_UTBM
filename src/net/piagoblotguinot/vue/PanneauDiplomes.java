package net.piagoblotguinot.vue;

import net.piagoblotguinot.controleur.Controleur;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class PanneauDiplomes extends JPanel
{
    private Controleur controleur;
    private Point emplacement;

    public PanneauDiplomes(Controleur controleur, Point emplacement)
    {
        this.controleur = controleur;
        this.emplacement = emplacement;
        initialiser();
    }

    private void initialiser()
    {
        this.setLayout(new FlowLayout());
        this.setBounds(emplacement.x,emplacement.y,754,400);
        this.setBackground(new Color(51, 48, 48));

        for(int i = 1; i <20;i++)
        {
            try {
                BufferedImage original = ImageIO.read(new File("data/diplomes/"+i+".png"));
                Image image = original.getScaledInstance((512/6), (600/6), Image.SCALE_SMOOTH);
                                                            // 146*105

                ImageIcon icone = new ImageIcon(image);
                JLabel label = new JLabel(icone);
                this.add(label);
            } catch (Exception e) {
            }
        }
    }


}
