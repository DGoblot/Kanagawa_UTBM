package net.piagoblotguinot.modèle;

public class Competence
{
    String domaine;
    String objet;
    int ects;
    Ordinateur ordi;

    int identifiant; //Numéro de la carte corresepondante

    public Competence()
    {
        this.domaine = "None";
        this.objet = "None";
        this.ects = 0;
        this.ordi = new Ordinateur();
    }

    public int getIdentifiant() {
        return identifiant;
    }
}
