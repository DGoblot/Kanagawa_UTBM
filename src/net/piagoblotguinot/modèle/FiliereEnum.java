package net.piagoblotguinot.modèle;

import java.util.Objects;

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

    protected void aff(){
        System.out.println("Type : " + type);
        System.out.println("Crédits : " + ects);
        if (stage)
            System.out.println("Stage : oui");
        else
            System.out.println("Stage : non");
        System.out.println("Objet : " + objet);
        System.out.print("Condition : Avoir les langages suivants :");
        for (String s : liste) {
            System.out.print(" " + s);
        }
        System.out.println();
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
