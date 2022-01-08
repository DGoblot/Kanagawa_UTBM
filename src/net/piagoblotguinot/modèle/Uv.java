package net.piagoblotguinot.modèle;



public class Uv
{
    String annee;
    String domaine;
    int nombre;
    int ects;
    String approche;
    String type;

    int identifiant; //Numéro de la carte correspondante

    public Uv()
    {
        this.annee = "None";
        this.domaine = "None";
        this.nombre = 0;
        this.ects = 0;
        this.approche = "None";
        this.type = "None";
    }

    public int getIdentifiant() {
        return identifiant;
    }
}
