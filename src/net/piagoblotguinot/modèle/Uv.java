package net.piagoblotguinot.modèle;

public class Uv
{
    String annee;
    String domaine;
    int nombre;
    int ects;
    String approche;
    String type;

    int identifiant;

    public Uv()
    {
        this.annee = "None";
        this.domaine = "None";
        this.nombre = 0;
        this.ects = 0;
        this.approche = "None";
        this.type = "None";
    }

    public void aff() {

        System.out.println("Domaine : " + domaine);
        System.out.println("Nombre de domaines : " + nombre);
        System.out.println("Année : " + annee);
        System.out.println("Approche : " + approche);
        System.out.println("Type : " + type);
        System.out.println("ECTS : " + ects);

    }

    public int getIdentifiant() {
        return identifiant;
    }
}