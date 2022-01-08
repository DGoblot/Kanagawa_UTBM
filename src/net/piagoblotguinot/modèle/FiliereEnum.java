package net.piagoblotguinot.modèle;

import java.util.Objects;

/*Spécialisation de filière pour les filières de la forme "Avoir X, X, et X"*/
public class FiliereEnum extends Filiere
{
    String[] liste;

    public FiliereEnum(String type, int ects, boolean stage, String objet, String[] liste)
    {
        this.type = type;
        this.ects = ects;
        this.stage = stage;
        this.objet = objet;
        this.liste = liste;

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
    
    /*Teste si la condition de la filière est remplie*/
    private boolean testCondition(Joueur joueur) {
        int n = liste.length;
        boolean present;
        for (String s : liste) {
            present = false;
            for (Uv uv : joueur.uvs) {
                if (Objects.equals(uv.type, s)) {
                    present = true;
                    break;
                }
            }
            if (present)
                n--;
        }
        return n == 0;
    }
}
