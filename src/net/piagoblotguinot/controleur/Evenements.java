package net.piagoblotguinot.controleur;

import net.piagoblotguinot.modèle.*;
import net.piagoblotguinot.vue.Ecran;
import net.piagoblotguinot.vue.PanneauJeu;

import javax.sound.sampled.*;
import java.io.File;
import java.util.ArrayList;

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

    public void retourMenuPrincipal()
    {
        bruitBouton();
        this.ecran.changerEtat(Etats.MENU);
        this.ecran.setSize(780, 500);
        this.ecran.setLocationRelativeTo(null);
    }

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
        controleur.nbJoueurs = nbJoueurs;
        boolean valide = true;

        /* Tester si les joueurs ont donné des noms valides (pas de chaines vides ou blanches) */
        for(int i = 0; i < nbJoueurs; i++)
        {
            controleur.tableauNoms[i] = this.ecran.getPanneauNouvellePartie().getTextFieldJoueur(i);
            if(controleur.tableauNoms[i] == null || controleur.tableauNoms[i].isEmpty() || controleur.tableauNoms[i].trim().isEmpty())
            {
                valide = false;
            }
        }
        if(valide) /* Lancer une nouvelle partie */
        {
            this.partie = null;
            this.partie = new Partie(nbJoueurs, controleur.tableauNoms);
            this.partie.init();
            this.ecran.setPanneauJeu(new PanneauJeu(this.controleur));
            this.ecran.changerEtat(Etats.PARTIE_EN_COURS);
            this.controleur.run(this.partie);
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
        System.out.println("Ici2");
        this.choix = 2;
    }
    public void poserUV()
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

        choix = this.ecran.getPanneauJeu().getPanneauAction().prendreFiliere(filiere);


        return (choix == 1);
    }


    public boolean getChoixJoueur()
    {

        switch (this.ecran.getPanneauJeu().getPanneauAction().choisirPasser()){
            case "Passer" -> choix = 0;
            case "Prendre des cartes" -> choix = 1;
        }

        return choix == 1;
    }

    public int getChoixColonne(boolean tab[])
    {
        // choix = Demander a la vue d'afficher le popup en donnnant en parametre tab
        choix = -1;
        while(choix == -1)
        {
            choix = this.ecran.getPanneauJeu().getPanneauAction().choisirColonne();
        }



        return choix-1;
    }

    public int getChoixAction()
    {
        // choix = Demander a la vue d'afficher le popup en donnnant en parametre tab
        choix = -1;
        while(choix == -1)
        {
            choix = this.ecran.getPanneauJeu().getPanneauAction().choisirAction();
        }



        return choix;
    }

    /*
    public int getChoixAction()
    {
        System.out.println("choix action");
        choix = -1;

        while(choix == -1){
            System.out.print("");
        }

        return choix;
    }*/

    public int getChoixCarte(int nombreCartesEnMain)
    {
        // choix = Affiche une popup qui demande juste un numéro prend en parametre nombreDeCartes
        choix = -1;
        while(choix == -1){
            choix = this.ecran.getPanneauJeu().getPanneauAction().choisirCarte();
        }



        return choix;
    }

    public void ecranFinDePartie()
    {
        this.ecran.changerEtat(Etats.FIN_JEU);

    }

    public void updateView(Partie partie)
    {
        updatePlateau(partie.getPlateau());

    }

    public void afficherMain(ArrayList<Carte> main){
        this.ecran.getPanneauJeu().getPanneauAction().ajouterCartesPrises(main);
    }

    private void updatePlateau(Carte[][] plateau) {
        this.ecran.getPanneauJeu().getPanneauUvs().afficherPlateau(plateau);
    }

    public void ajouterUV(int i, Uv uv) {
        this.ecran.getPanneauJeu().getPanneauJoueur(i).ajouterUv(uv);

    }
    public void ajouterCompetence(int i, Competence competence) {
        this.ecran.getPanneauJeu().getPanneauJoueur(i).ajouterCompetence(competence);

    }

    public void retirerFiliere(int i){
        this.ecran.getPanneauJeu().getPanneauFilieres().retirerFiliere(i-1);

    }






    /* ETAT : FIN_JEU */

}
