package net.piagoblotguinot.modèle;

import java.util.Objects;

public class FiliereIdentique extends Filiere
{
    int nombre;

    public FiliereIdentique(String type, int ects, boolean stage, String objet, int nombre)
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
        System.out.println("Condition : Avoir " + nombre + " " + type + " (identiques)");
        System.out.println();
    }

    protected boolean disponible(Joueur joueur){
        boolean dispo = false;
        if (skip[joueur.numeroAbsolu-1])
            return false;

        for (Filiere filiere : joueur.filieres) {
            if (Objects.equals(filiere.type, this.type))
                return false;
        }

        switch (this.type){
            case "ordinateur", "fleche" -> dispo = testObjet(this.type,joueur);
            case "approche" -> dispo = testDomaine(joueur);
            case "modélisation" -> dispo = testModelisation(joueur);
        }

        return dispo;


    }

    private boolean testModelisation(Joueur joueur) {
        int max,total;
        max = 0;
        for (Uv uv1 : joueur.uvs) {
            total = 0;
            for (Uv uv2 : joueur.uvs) {
                if (Objects.equals(uv1.type,uv2.type) && Objects.equals(uv1.type, "modélisation")){
                    total++;
                }
            }
            if (total > max)
                max = total;

        }

        return max >= this.nombre;

    }

    private boolean testDomaine(Joueur joueur) {
        int max, total;
        max = 0;
        String[] domaines = {"reseau","robotique","maths","bdd","algo"};
        for (String domaine : domaines) {
            total = 0;
            for (Competence competence : joueur.competences) {
                if (Objects.equals(competence.domaine, domaine))
                    total++;
            }
            if (total > max)
                max = total;

        }
        return max >= this.nombre;
    }

    private boolean testObjet(String type, Joueur joueur) {
        int total = 0;
        if (Objects.equals(type, "ordinateur")){
            total = 2;
            type = "ordi";
        }

        if (Objects.equals(type, "fleche"))
            total = 1;

        for (Competence competence : joueur.competences) {
            if (Objects.equals(competence.objet, type))
                total++;
        }

        return total >= this.nombre;
    }
}
