package net.piagoblotguinot.controleur;

import net.piagoblotguinot.modèle.Filiere;
import net.piagoblotguinot.modèle.Joueur;
import net.piagoblotguinot.modèle.Partie;
import net.piagoblotguinot.vue.Ecran;

import javax.sound.sampled.*;
import java.io.File;

public class Evenements
{
    private Controleur controleur;
    private Partie partie;
    private Ecran ecran;
    private int choix = -1;

    public Evenements(Controleur controleur, Partie partie, Ecran ecran)
    {
        this.controleur = controleur;
        this.partie = partie;
        this.ecran = ecran;
    }

    /* ETAT : MENU */

    public void nouvellePartie() {bruitBouton(); this.ecran.changerEtat(Etats.NOUVELLE_PARTIE);}
    public void historique() {bruitBouton(); this.ecran.changerEtat(Etats.HISTORIQUE);}
    public void credits() {bruitBouton(); this.ecran.getPanneauMenu().afficherCredits();}
    public void quitter() {bruitBouton(); System.exit(0);}

    public void bruitBouton()
    {
        File file = new File("data/bouton.wav");
        try
        {
            AudioInputStream stream = AudioSystem.getAudioInputStream(file);
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            clip.start();

        }
        catch (Exception e)
        {
            System.out.println("Problème avec l'ouverture du son");
            e.printStackTrace();
        }
    }

    /* ETAT : HISTORIQUE */

    public void retourMenuPrincipal() {bruitBouton();this.ecran.changerEtat(Etats.MENU);}

    /* ETAT : NOUVELLE_PARTIE */

    public void ajouterJoueur()
    {
        bruitBouton();
        this.ecran.getPanneauNouvellePartie().ajouterJoueur();
    }

    public void supprimerJoueur()
    {
        bruitBouton();
        this.ecran.getPanneauNouvellePartie().supprimerJoueur();
    }

    public void valider()
    {
        bruitBouton();
        int nbJoueurs = this.ecran.getPanneauNouvellePartie().getNbJoueurs();
        String[] nomsJoueurs = new String[4];
        boolean valide = true;

        /* Tester si les joueurs ont donné des noms valides (pas de chaines vides ou blanches) */
        for(int i = 0; i < nbJoueurs; i++)
        {
            nomsJoueurs[i] = this.ecran.getPanneauNouvellePartie().getTextFieldJoueur(i);
            if(nomsJoueurs[i] == null || nomsJoueurs[i].isEmpty() || nomsJoueurs[i].trim().isEmpty())
            {
                valide = false;
            }
        }
        if(valide) /* Lancer une nouvelle partie */
        {
            this.partie = null;
            this.partie = new Partie(nbJoueurs, nomsJoueurs);
            this.ecran.changerEtat(Etats.PARTIE_EN_COURS);
        }
        else /* Demander aux joueurs de donner des pseudos corrects */
        {
            this.ecran.getPanneauNouvellePartie().joueurSansNom();
        }
    }

    /* ETAT : PARTIE_EN_COURS */



    public void passer()
    {
        this.choix = 0;


    }

    public void prendre()
    {
        this.choix = 1;
    }
    public void poserCompetence()
    {
        this.choix = 2;
    }
    public void poserUniteDeValeur()
    {
        this.choix = 1;
    }

    public void prendre1(){}
    public void prendre2(){}
    public void prendre3(){}
    public void prendre4(){}
    public void prendreFiliere()
    {
        choix = 1;
    }
    public void aImplementer(){}

    public void poserOrdi()
    {
        this.choix = 3;
    }
    public void bougerOrdi()
    {
        this.choix = 4;
    }

    public void finDuTour()
    {
      this.choix = 5;

    }

    public boolean getChoixFiliere(Filiere filiere)
    {
        // Demander à la vue d'afficher un popup pour le choix de prendre la filiere ou non

        choix = -1;
        while(choix == -1){}


        return (choix == 1);
    }


    public boolean getChoixJoueur()
    {
        // Demader à la vue d'afficher un popup

        choix = -1;
        while(choix == -1){}



        return (choix == 1);
    }

    public int getChoixColonne(boolean tab[])
    {
        // choix = Demander a la vue d'afficher le popup en donnnant en parametre tab
        choix = -1;
        while(choix == -1){}



        return choix;
    }

    public int getChoixAction()
    {
        choix = -1;
        while(choix == -1){}


        return choix;
    }

    public int getChoixCarte(int nombreCartesEnMain)
    {
        // choix = Affiche une popup qui demande juste un numéro prend en parametre nombreDeCartes
        choix = -1;
        while(choix == -1){}



        return choix;
    }

    public void ecranFinDePartie(Joueur[] tab)
    {
        // Change l'etat du jeu
        //Affiche fin de partie


    }

    public void updateView(Partie partie)
    {
        // UPDATE LA VUE

    }






    /* ETAT : FIN_JEU */

}
