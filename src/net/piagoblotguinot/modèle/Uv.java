package net.piagoblotguinot.modèle;

public class Uv
{
    String annee;
    String domaine;
    String nombre;
    String ects;
    String approche;
    String type;

    public Uv()
    {
        this.annee = "None";
        this.domaine = "None";
        this.nombre = "None";
        this.ects = "None";
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
}