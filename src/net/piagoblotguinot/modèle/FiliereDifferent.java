package net.piagoblotguinot.modèle;

import java.util.Objects;

public class FiliereDifferent extends Filiere
{
    int nombre;

    public FiliereDifferent(String type, int ects, boolean stage, String objet, int nombre)
    {
        this.type = type;
        this.ects = ects;
        this.stage = stage;
        this.objet = objet;
        this.nombre = nombre;
    }

    protected void aff(){
        System.out.println("Type : " + type);
        System.out.println("Crédits : " + ects);
        if (stage)
            System.out.println("Stage : oui");
        else
            System.out.println("Stage : non");
        System.out.println("Objet : " + objet);
        System.out.println("Condition : Avoir " + nombre + " " + type + " différents");
        System.out.println();
    }

    protected boolean disponible(Joueur joueur){
        if (skip[joueur.numeroAbsolu-1])
            return false;

        for (Filiere filiere : joueur.filieres) {
            if (Objects.equals(filiere.type, this.type))
                return false;
        }

        return testCondition(joueur);


    }

    private boolean testCondition(Joueur joueur) {
        boolean nouveau;
        int total = 0;
        for (int i = 0; i < joueur.uvs.size(); i++) {
        nouveau = true;
            for (int j = 0; j < i; j++) {
                if (Objects.equals(joueur.uvs.get(i).type, joueur.uvs.get(j).type)) {
                    nouveau = false;
                    break;
                }
            }
            if (Objects.equals(joueur.uvs.get(i).approche, this.type) && nouveau)
            {
                total++;
            }
        }
        return (total >= this.nombre);
    }
}