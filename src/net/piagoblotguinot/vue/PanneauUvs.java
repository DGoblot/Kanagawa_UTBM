package net.piagoblotguinot.vue;

import net.piagoblotguinot.controleur.Controleur;
import net.piagoblotguinot.modèle.Carte;
import net.piagoblotguinot.utilitaires.Utilitaires;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
/*
        Classe gérant le plateau central
*/
public class PanneauUvs extends JPanel {
    private Controleur controleur;
    private JLabel[] images = new JLabel[12];

    private Point emplacement;
    private final int LARGEUR = 617, HAUTEUR = 334; // Dimensions du panneau uvs

    public PanneauUvs(Controleur controleur, Point emplacement) {
        this.controleur = controleur;
        this.emplacement = emplacement;
        initialiser();
    }

    private void initialiser() {

        BufferedImage original = null;
        try {
            original = ImageIO.read(new File("data/cartes/Carte_vide.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon carteVide = new ImageIcon(original);

        this.setLayout(new GridLayout(3, 4));
        this.setBounds(emplacement.x, emplacement.y, this.LARGEUR, this.HAUTEUR);
        this.setBackground(Color.DARK_GRAY);

        this.setBackground(new Color(81, 81, 81));
        for (int i = 1; i < 13; i++) {
            try {
                BufferedImage original2 = ImageIO.read(new File("data/cartes/Carte_" + i + ".png"));
                Image image = original2.getScaledInstance((1024 / 7), (734 / 7), Image.SCALE_SMOOTH);
                // 146*105

                ImageIcon icone2 = new ImageIcon(image);
                JLabel label = new JLabel(icone2);
                this.add(label);
                images[i - 1] = label;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //images[3].setIcon(carteVide);


    }
    /*
        Vide le plateau central de toutes ses cartes (reset)
    */
    private void viderPlateau() {

        BufferedImage original = null;
        try {
            original = ImageIO.read(new File("data/cartes/Carte_vide.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon carteVide = new ImageIcon(original);

        for (int i = 1; i < 13; i++) {
            images[i-1].setIcon(carteVide);

        }

    }
    /*
        Affiche les cartes présentes sur le plateau central
    */
    public void afficherPlateau(Carte[][] plateau) {

        viderPlateau();



        BufferedImage original2;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {

                try {
                    if (plateau[i][j] != null) {
                        original2 = ImageIO.read(new File("data/cartes/Carte_" + plateau[i][j].getUv().getIdentifiant() + ".png"));
                    } else {
                        original2 = ImageIO.read(new File("data/cartes/Carte_vide.png"));
                    }
                    Image image = original2.getScaledInstance((1024 / 7), (734 / 7), Image.SCALE_SMOOTH);
                    // 146*105

                    ImageIcon icone2 = new ImageIcon(image);
                    images[4*(i)+j].setIcon(icone2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }
}
