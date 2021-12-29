package net.piagoblotguinot.vue;

import net.piagoblotguinot.controleur.Controleur;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class PanneauJeu extends JPanel
{
    private static final Point POS_JOUEUR1 = new Point(0,0);
    private static final Point POS_JOUEUR2 = new Point(0,401);
    private static final Point POS_JOUEUR3 = new Point(0,0);
    private static final Point POS_JOUEUR4 = new Point(0,0);
    private static final Point POS_PLATEAU = new Point(480,401);
    private static final Point POS_ZONE_ACTION = new Point(0,0);

    private Controleur controleur;

    //La zone d'un joueur : Un JPanel "PanneauJoueur qui contient :
    //                      - Un JPanel uvs
    //                      - Un JPanel competences
    //                      - Un JPanel informations (numero, nom, nb d'ordis, de mouvements...)
    private PanneauJoueur[] panneauJoueurs;

    private PanneauAction panneauAction; // Panel où se trouvent les boutons pour interragir avec le jeu
    private PanneauUvs panneauUvs; // Plateau où sont distribuées les cartes d'Uvs

    public PanneauJeu(Controleur controleur)
    {
        this.controleur = controleur;
        this.panneauJoueurs = new PanneauJoueur[4];
        this.panneauJoueurs[0] = new PanneauJoueur(this.controleur, POS_JOUEUR1);
        this.panneauJoueurs[1] = new PanneauJoueur(this.controleur, POS_JOUEUR2);
        this.panneauAction = new PanneauAction(this.controleur);
        this.panneauUvs = new PanneauUvs(this.controleur,POS_PLATEAU);

        initialiser();
    }

    public void initialiser()
    {
        this.setBackground(new Color(2,4, 19));
        this.setLayout(null);

        this.add(panneauJoueurs[0]);
        this.add(panneauJoueurs[1]);
        this.add(panneauUvs);
    }
}


