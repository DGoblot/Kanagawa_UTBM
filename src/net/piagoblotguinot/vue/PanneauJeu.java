package net.piagoblotguinot.vue;

import net.piagoblotguinot.controleur.Controleur;

import javax.swing.*;
import java.awt.*;
/*
        Panneau contenant et affichant la partie en cours (plateau central, joueurs, etc)
*/
public class PanneauJeu extends JPanel
{
    private static final Point POS_JOUEUR1 = new Point(0,0);    // HAUT GAUCHE
    private static final Point POS_JOUEUR2 = new Point(1326,0); // HAUT DROIT
    private static final Point POS_JOUEUR3 = new Point(0,515);    // BAS GAUCHE
    private static final Point POS_JOUEUR4 = new Point(1326,515);    // BAS DROIT
    private static final Point POS_PLATEAU = new Point(571+69,320);
    private static final Point POS_DIPLOME = new Point(571,0);
    private static final Point POS_ACTION = new Point(571,655);

    private Controleur controleur;

    private PanneauJoueur[] panneauJoueurs;
    private PanneauAction panneauAction;
    private PanneauUvs panneauUvs;
    private PanneauFilieres panneauFilieres;

    public PanneauJeu(Controleur controleur)
    {
        this.controleur = controleur;
        this.panneauJoueurs = new PanneauJoueur[4];
        this.panneauJoueurs[0] = new PanneauJoueur(this.controleur, POS_JOUEUR1,1);
        this.panneauJoueurs[1] = new PanneauJoueur(this.controleur, POS_JOUEUR2,2);
        this.panneauJoueurs[2] = new PanneauJoueur(this.controleur, POS_JOUEUR3,3);
        this.panneauJoueurs[3] = new PanneauJoueur(this.controleur, POS_JOUEUR4,4);

        this.panneauAction = new PanneauAction(this.controleur,POS_ACTION);
        this.panneauUvs = new PanneauUvs(this.controleur,POS_PLATEAU);
        this.panneauFilieres = new PanneauFilieres(this.controleur,POS_DIPLOME);

        initialiser();
    }

    public void initialiser()
    {
        this.setBackground(new Color(23, 23, 23));
        this.setLayout(null);

        for (int i = 0; i < controleur.getnbJoueurs(); i++) {

    
        this.add(panneauJoueurs[i]);
    }

        this.add(panneauAction);
        this.add(panneauUvs);
        this.add(panneauFilieres);
    }

    public PanneauUvs getPanneauUvs() {
        return panneauUvs;
    }

    public PanneauFilieres getPanneauFilieres() {
        return panneauFilieres;
    }

    public PanneauJoueur getPanneauJoueur(int i) {
        return this.panneauJoueurs[i];

    }

    public PanneauAction getPanneauAction() {return this.panneauAction;}

}


