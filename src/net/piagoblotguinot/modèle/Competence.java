package net.piagoblotguinot.modèle;

public class Competence
{
    String domaine;
    String objet;
    String ects;
    boolean ordi;
    boolean actif;

    public Competence()
    {
        this.domaine = "None";
        this.objet = "None";
        this.ects = "None";
        this.ordi = false;
        this.actif = false;
    }
}
