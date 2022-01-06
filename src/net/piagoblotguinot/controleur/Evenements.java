package net.piagoblotguinot.controleur;

import net.piagoblotguinot.modèle.Partie;
import net.piagoblotguinot.vue.Ecran;

import javax.sound.sampled.*;
import java.io.File;

public class Evenements
{
    private Controleur controleur;
    private Partie partie;
    private Ecran ecran;

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



    }

    public void prendre(){}
    public void poserCompetence(){}
    public void poserUniteDeValeur(){}
    public void garderEnMain(){}
    public void prendre1(){}
    public void prendre2(){}
    public void prendre3(){}
    public void prendre4(){}
    public void diplomeDeclenche(){}





    /* ETAT : FIN_JEU */

}
