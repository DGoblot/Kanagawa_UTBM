package net.piagoblotguinot.vue;

import net.piagoblotguinot.controleur.Controleur;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class PanneauJeu extends JPanel
{
    private static final Point POS_JOUEUR1 = new Point(0,0);    // HAUT GAUCHE
    private static final Point POS_JOUEUR2 = new Point(1326,0); // HAUT DROIT
    private static final Point POS_JOUEUR3 = new Point(0,515);    // BAS GAUCHE
    private static final Point POS_JOUEUR4 = new Point(1326,515);    // BAS DROIT
    private static final Point POS_PLATEAU = new Point(0,0);
    private static final Point POS_DIPLOME = new Point(571,0);
    private static final Point POS_ACTION = new Point(0,0);

    private Controleur controleur;

    //La zone d'un joueur : Un JPanel "PanneauJoueur qui contient :
    //                      - Un JPanel uvs
    //                      - Un JPanel competences
    //                      - Un JPanel informations (numero, nom, nb d'ordis, de mouvements...)
    private PanneauJoueur[] panneauJoueurs;

    private PanneauAction panneauAction; // Panel où se trouvent les boutons pour interragir avec le jeu
    private PanneauUvs panneauUvs; // Plateau où sont distribuées les cartes d'Uvs
    private PanneauDiplomes panneauDiplomes;

    public PanneauJeu(Controleur controleur)
    {
        this.controleur = controleur;
        this.panneauJoueurs = new PanneauJoueur[4];
        this.panneauJoueurs[0] = new PanneauJoueur(this.controleur, POS_JOUEUR1);
        this.panneauJoueurs[1] = new PanneauJoueur(this.controleur, POS_JOUEUR2);
        this.panneauJoueurs[2] = new PanneauJoueur(this.controleur, POS_JOUEUR3);
        this.panneauJoueurs[3] = new PanneauJoueur(this.controleur, POS_JOUEUR4);
        //this.panneauAction = new PanneauAction(this.controleur,POS_ACTION);
        this.panneauDiplomes = new PanneauDiplomes(this.controleur,POS_DIPLOME);


        //this.panneauUvs = new PanneauUvs(this.controleur,POS_PLATEAU);

        initialiser();
    }

    public void initialiser()
    {
        this.setBackground(new Color(16, 16, 58));
        this.setLayout(null);

        this.add(panneauJoueurs[0]);
        this.add(panneauJoueurs[1]);
        this.add(panneauJoueurs[2]);
        this.add(panneauJoueurs[3]);
        this.add(panneauDiplomes);
        //this.add(panneauUvs);
    }
}


