package net.piagoblotguinot.controleur;

import net.piagoblotguinot.modèle.Carte;
import net.piagoblotguinot.modèle.Partie;
import net.piagoblotguinot.vue.Ecran;
/*
        Classe controleur du programme 
*/
public class Controleur {
    private Partie partie;
    public boolean lancer = false;
    private Ecran ecran;
    private Evenements evenements;
    String[] tableauNoms = new String[4];
    int nbJoueurs;

    public Controleur() {
        for (int i = 0; i < 4; i++) {
            tableauNoms[i] = "test";
        }

    }
    /*
        retourne les noms des joueurs
    */
    public String[] nomsJoueurs(){

        return tableauNoms;

    }

    public void init(){
        this.ecran = new Ecran(this);


        this.evenements = new Evenements(this, this.partie, this.ecran);

        //this.partie = new Partie(nbJoueurs,tableauNoms);
        //partie.init();
    }

    /*
        Fonction principale qui lance la partie
    */
    public void run(Partie partie){
        this.partie = partie;
        partie.run();
    }


    /* GETTERS */
    public Evenements getEvenements() {
        return this.evenements;
    }

    public Ecran getEcran() {
        return this.ecran;
    }

    public int getnbJoueurs() {
        return nbJoueurs;
    }

    public int[] getScores() {

        return partie.getScores();

    }

    public String[] getNoms() {
        return partie.getNomsJoueurs();
    }

    public void finJeu() {
        this.evenements.ecranFinDePartie();
    }
}
