package net.piagoblotguinot.vue;

import net.piagoblotguinot.controleur.Controleur;

import javax.swing.*;
import java.awt.*;

public class PanneauAction extends JPanel
{
    private static final Point POS_BOUTONPASSER = new Point(0,0);
    private static final Point POS_BOUTONPRENDRE = new Point(1326,0);
    private static final Point POS_BOUTONCHOIXCOLONNE = new Point(0,515);
    private static final Point POS_BOUTONPOSERORDI = new Point(1326,515);
    private static final Point POS_BOUTONBOUGERORDI = new Point(571+69,320);
    private static final Point POS_BOUTONPOSERUV = new Point(571,0);
    private static final Point POS_BOUTONPOSERCOMPETENCE = new Point(0,0);
    private static final Point POS_BOUTONPRENDREDIPLOME = new Point(571,0);
    private static final Point POS_BOUTONFINIRTOUR = new Point(571,0);

    private Controleur controleur;
    private Point emplacement;

    private Bouton boutonPasser;
    private Bouton boutonPrendre;
    private Bouton[] boutonChoixColonne;
    private Bouton boutonPoserOrdi;
    private Bouton boutonBougerOrdi;
    private Bouton boutonPoserUV;
    private Bouton boutonPoserCompetence;
    private Bouton boutonPrendreDiplome;
    private Bouton boutonFinirTour;
    private JDialog choixAction;

    public PanneauAction(Controleur controleur,Point emplacement)
    {
        this.controleur = controleur;
        this.emplacement = emplacement;

        this.boutonPasser = creerBoutonPasser();
        this.boutonPrendre = creerBoutonPrendre();
        this.boutonChoixColonne = new Bouton[4];
        for(int i = 0 ; i < 4 ; i++)
        {
            this.boutonChoixColonne[i] = creerBoutonChoixColonne(i);
        }
        this.boutonPoserOrdi = creerBoutonPoserOrdi();
        this.boutonBougerOrdi = creerBoutonBougerOrdi();
        this.boutonPoserUV = creerBoutonPoserUV();
        this.boutonPoserCompetence = creerBoutonPoserCompetence();
        this.boutonPrendreDiplome = creerBoutonPrendreDiplome();
        this.boutonFinirTour = creerBoutonFinirTour();
        this.choixAction = creerChoixAction();

    }

    private JDialog creerChoixAction()
    {
        JDialog dialog = new JDialog();
        //dialog.setType(JDialog.);

        return dialog;
    }

    private Bouton creerBoutonPasser()
    {
        Bouton bouton = new Bouton("Passer");
        bouton.setLocation(POS_BOUTONPASSER);
        bouton.addActionListener(e -> this.controleur.getEvenements().passer());
        return bouton;
    }

    private Bouton creerBoutonPrendre()
    {
        Bouton bouton = new Bouton("Prendre");
        bouton.setLocation(POS_BOUTONPRENDRE);
        bouton.addActionListener(e -> this.controleur.getEvenements().prendre());
        return bouton;
    }

    private Bouton creerBoutonChoixColonne(int i)
    {
        Bouton bouton = new Bouton("Choix colonne");
        bouton.setLocation(POS_BOUTONCHOIXCOLONNE);
        bouton.addActionListener(e -> this.controleur.getEvenements().aImplementer());
        return bouton;
    }

    private Bouton creerBoutonPoserOrdi()
    {
        Bouton bouton = new Bouton("Poser ordi");
        bouton.setLocation(POS_BOUTONPOSERORDI);
        bouton.addActionListener(e -> this.controleur.getEvenements().poserOrdi());
        return bouton;
    }

    private Bouton creerBoutonBougerOrdi()
    {
        Bouton bouton = new Bouton("Bouger ordi");
        bouton.setLocation(POS_BOUTONBOUGERORDI);
        bouton.addActionListener(e -> this.controleur.getEvenements().bougerOrdi());
        return bouton;
    }

    private Bouton creerBoutonPoserUV()
    {
        Bouton bouton = new Bouton("Poser en tant qu'UV");
        bouton.setLocation(POS_BOUTONPOSERUV);
        bouton.addActionListener(e -> this.controleur.getEvenements().poserUniteDeValeur());
        return bouton;
    }

    private Bouton creerBoutonPoserCompetence()
    {
        Bouton bouton = new Bouton("Poser en tant que compétence");
        bouton.setLocation(POS_BOUTONPOSERCOMPETENCE);
        bouton.addActionListener(e -> this.controleur.getEvenements().poserCompetence());
        return bouton;
    }
    private Bouton creerBoutonPrendreDiplome()
    {
        Bouton bouton = new Bouton("Prendre le diplome ?");
        bouton.setLocation(POS_BOUTONPRENDREDIPLOME);
        bouton.addActionListener(e -> this.controleur.getEvenements().prendreFiliere());
        return bouton;
    }
    private Bouton creerBoutonFinirTour()
    {
        Bouton bouton = new Bouton("Finir le tour");
        bouton.setLocation(POS_BOUTONFINIRTOUR);
        bouton.addActionListener(e -> this.controleur.getEvenements().finDuTour());
        return bouton;
    }







}
