package net.piagoblotguinot.modèle;

import java.util.Objects;

/*Spécialisation de filière pour les conditions de type : "Avoir X items différents"*/

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

    protected boolean disponible(Joueur joueur){
        if (skip[joueur.numeroAbsolu-1])
            return false;

        for (Filiere filiere : joueur.filieres) {
            if (Objects.equals(filiere.type, this.type))
                return false;
        }

        return testCondition(joueur);


    }
    
    /*Teste que la condition de la filière est remplie*/
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
