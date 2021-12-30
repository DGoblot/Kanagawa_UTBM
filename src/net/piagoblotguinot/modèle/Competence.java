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

    public void aff(boolean posee) {

        System.out.println("Domaine : " + domaine);
        System.out.println("Objet : " + objet);
        System.out.println("ECTS : " + ects);
        if (posee)
        {
            System.out.println("Ordi présent : " + ordi);
            System.out.println("Compétence active : " + actif);
        }

    }
}
