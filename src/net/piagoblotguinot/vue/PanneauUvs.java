package net.piagoblotguinot.vue;

import net.piagoblotguinot.controleur.Controleur;
import net.piagoblotguinot.utilitaires.Utilitaires;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class PanneauUvs extends JPanel
{
    private Controleur controleur;
    private Point emplacement;
    private final int LARGEUR = 617, HAUTEUR = 334; // Dimensions du panneau uvs

    public PanneauUvs(Controleur controleur, Point emplacement)
    {
        this.controleur = controleur;
        this.emplacement = emplacement;
        initialiser();
    }

    private void initialiser()
    {
        this.setLayout(new FlowLayout());
        this.setBounds(emplacement.x,emplacement.y,this.LARGEUR,this.HAUTEUR);
        this.setBackground(Color.DARK_GRAY);

        this.setBackground(new Color(48, 211, 0));
        for(int i = 1; i <13;i++)
        {
            try
            {
                BufferedImage original = ImageIO.read(new File("data/cartes/Carte_"+ i +".png"));
                Image image = original.getScaledInstance((1024/7),(734/7),Image.SCALE_SMOOTH);
                                                            // 146*105

                ImageIcon icone = new ImageIcon(image);
                JLabel label = new JLabel(icone);
                this.add(label);
            } catch(Exception e){e.printStackTrace();}
        }

    }

}
