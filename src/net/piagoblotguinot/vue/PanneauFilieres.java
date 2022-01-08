package net.piagoblotguinot.vue;

import net.piagoblotguinot.controleur.Controleur;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/*
        Classe du panneau d'affichage des filières disponibles
*/
public class PanneauFilieres extends JPanel
{
    private Controleur controleur;
    private Point emplacement;
    private JLabel[] filieres = new JLabel[19];

    public PanneauFilieres(Controleur controleur, Point emplacement)
    {
        this.controleur = controleur;
        this.emplacement = emplacement;
        initialiser();
    }

    private void initialiser()
    {
        this.setLayout(new FlowLayout());
        this.setBounds(emplacement.x,emplacement.y,754,320);
        this.setBackground(new Color(45, 45, 45));

        for(int i = 1; i <20;i++)
        {
            try {
                BufferedImage original = ImageIO.read(new File("data/diplomes/"+i+".png"));
                Image image = original.getScaledInstance((512/6), (600/6), Image.SCALE_SMOOTH);
                                                            // 146*105

                ImageIcon icone = new ImageIcon(image);
                JLabel label = new JLabel(icone);
                this.add(label);
                filieres[i-1] = label;
            } catch (Exception e) {
            }
        }
    }
    /*
        Permet de retirer une filière du panneau
    */
    public void retirerFiliere(int i){

            BufferedImage original = null;
            try {
                original = ImageIO.read(new File("data/diplomes/Filiere_vide.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            ImageIcon carteVide = new ImageIcon(original);

                filieres[i].setIcon(carteVide);



    }


}
