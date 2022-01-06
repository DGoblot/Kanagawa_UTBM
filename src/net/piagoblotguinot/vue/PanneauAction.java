package net.piagoblotguinot.vue;

import net.piagoblotguinot.controleur.Controleur;

import javax.swing.*;
import java.awt.*;

public class PanneauAction extends JPanel
{
    private static final Point POS_BOUTONPASSER = new Point(0,0);    // HAUT GAUCHE
    private static final Point POS_BOUTONPRENDRE = new Point(1326,0); // HAUT DROIT
    private static final Point POS_BOUTONPRENDRECOLONNE = new Point(0,515);    // BAS GAUCHE
    private static final Point POS_BOUTONPOSERORDI = new Point(1326,515);    // BAS DROIT
    private static final Point POS_BOUTONBOUGERORDI = new Point(571+69,320);
    private static final Point POS_BOUTONPOSERUV = new Point(571,0);
    private static final Point POS_BOUTONPOSERCOMPETENCE = new Point(0,0);
    private static final Point POS_BOUTONPRENDREDIPLOME = new Point(571,0);
    private static final Point POS_BOUTONFINIRTOUR = new Point(571,0);

    private Controleur controleur;
    private Point emplacement;

    private JButton boutonPasser;
    private JButton boutonPrendre;
    private JButton[] boutonPrendreColonne;
    private JButton boutonPoserOrdi;
    private JButton boutonBougerOrdi;
    private JButton boutonPoserUV;
    private JButton boutonPoserCompetence;
    private JButton boutonPrendreDiplome;
    private JButton boutonFinirTour;
/*
    public PanneauAction(Controleur controleur,Point emplacement)
    {
        this.controleur = controleur;
        this.emplacement = emplacement;

        this.boutonPasser = creerBoutonPasser;
        this.boutonPrendre = creerBoutonPrendre;
        this.boutonPrendreColonne = new JButton[4];
        for(int i = 0 ; i < 4 ; i++)
        {
            this.boutonPrendreColonne[i] = creerBoutonPrendreColonne(i);
        }
        this.boutonPoserOrdi = creerBoutonPoserOrdi;
        this.boutonBougerOrdi = creerBoutonBougerOrdi;
        this.boutonPoserUV = creerBoutonPoserUV;
        this.boutonPoserCompetence = creerBoutonPoserCompetence;
        this.boutonPrendreDiplome = creerBoutonPrendreDiplome;
        this.boutonFinirTour = creerBoutonFinirTour;





    }

    private JButton creerBoutonNouvellePartie()
    {
        JButton button = new JButton("Nouvelle partie");
        button.setForeground(new Color(2, 2, 12));
        button.setSize(dimensionBouton);
        button.setLocation(575,120);
        button.setBackground(Color.white);
        button.addActionListener(e -> this.controleur.getEvenements().nouvellePartie());
        return button;
    }

    private JButton creerBoutonPasser()
    {
        JButton button = new JButton("Passer");
        button.setForeground(new Color(2, 2, 12));
        button.setSize(dimensionBouton);
        button.setLocation(POS_BOUTONPASSER);
        button.setBackground(Color.white);
        //button.addActionListener(e -> this.controleur.getEvenements().nouvellePartie());
        return button;
    }
    private JButton creerBoutonPrendre()
    {
        JButton button = new JButton("Prendre");
        button.setForeground(new Color(2, 2, 12));
        button.setSize(dimensionBouton);
        button.setLocation (POS_BOUTONPRENDRE);
        button.setBackground(Color.white);
        //button.addActionListener(e -> this.controleur.getEvenements().nouvellePartie());
        return button;
    }

    private JButton creerBoutonPrendreColonne(int i)
    {
        JButton button = new JButton("Prendre la colonne " + i);
        button.setForeground(new Color(2, 2, 12));
        button.setSize(dimensionBouton);
        button.setLocation(POS_BOUTONPRENDRECOLONNE);
        button.setBackground(Color.white);
        //button.addActionListener(e -> this.controleur.getEvenements().nouvellePartie());
        return button;
    }

    private JButton creerBoutonPoserOrdi()
    {
        JButton button = new JButton("Poser un ordinateur");
        button.setForeground(new Color(2, 2, 12));
        button.setSize(dimensionBouton);
        button.setLocation(POS_BOUTONPOSERORDI);
        button.setBackground(Color.white);
        //button.addActionListener(e -> this.controleur.getEvenements().nouvellePartie());
        return button;
    }
    private JButton creerBoutonBougerOrdi()
    {
        JButton button = new JButton("Bouger un ordinateur");
        button.setForeground(new Color(2, 2, 12));
        button.setSize(dimensionBouton);
        button.setLocation(POS_BOUTONBOUGERORDI);
        button.setBackground(Color.white);
        //button.addActionListener(e -> this.controleur.getEvenements().nouvellePartie());
        return button;
    }

    private JButton creerBoutonPoserUV()
    {
        JButton button = new JButton("Poser une UV");
        button.setForeground(new Color(2, 2, 12));
        button.setSize(dimensionBouton);
        button.setLocation(POS_BOUTONPOSERUV);
        button.setBackground(Color.white);
        //button.addActionListener(e -> this.controleur.getEvenements().nouvellePartie());
        return button;
    }

    private JButton creerBoutonPoserCompetence()
    {
        JButton button = new JButton("Poser une compétence");
        button.setForeground(new Color(2, 2, 12));
        button.setSize(dimensionBouton);
        button.setLocation();
        button.setBackground(Color.white);
        //button.addActionListener(e -> this.controleur.getEvenements().nouvellePartie());
        return button;
    }

    private JButton creerBoutonPrendreDiplome()
    {
        JButton button = new JButton("Prendre un diplôme");
        button.setForeground(new Color(2, 2, 12));
        button.setSize(dimensionBouton);
        button.setLocation(575,120);
        button.setBackground(Color.white);
        //button.addActionListener(e -> this.controleur.getEvenements().nouvellePartie());
        return button;
    }

    private JButton creerBoutonFinirTour()
    {
        JButton button = new JButton("Finir son tour");
        button.setForeground(new Color(2, 2, 12));
        button.setSize(dimensionBouton);
        button.setLocation(575,120);
        button.setBackground(Color.white);
        //button.addActionListener(e -> this.controleur.getEvenements().nouvellePartie());
        return button;
    }







*/

}
