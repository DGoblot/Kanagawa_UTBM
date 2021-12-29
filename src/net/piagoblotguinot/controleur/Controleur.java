package net.piagoblotguinot.controleur;

import net.piagoblotguinot.modèle.Partie;
import net.piagoblotguinot.vue.Ecran;

public class Controleur
{
    private Partie partie;
    private Ecran ecran;
    private Evenements evenements;

    public Controleur()
    {
        this.ecran = new Ecran(this);
        this.evenements = new Evenements(this, this.partie, this.ecran);
        //Temporaire
        String[] tableau = new String[4];
        for (int i = 0; i < 4; i++) {
            tableau[i] = "test";
        }
        this.partie = new Partie(4,tableau);
        partie.init();
        partie.run();
    }

    /* GETTERS */
    public Evenements getEvenements() {return this.evenements;}
    public Ecran getEcran() {return this.ecran;}
}
