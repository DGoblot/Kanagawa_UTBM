package net.piagoblotguinot.modèle;

public class Filiere
{
    int identifiant;
    boolean[] skip;
    String type;
    int ects;
    boolean stage;
    String objet;

    public Filiere()
    {
        this.type = "None";
        this.ects = 0;
        stage = false;
        objet = "None";
        skip = new boolean[4];
        for (int i = 0; i < 4; i++) {
            skip[i] = false;
        }
    }

    protected void aff(){

    }

    protected boolean disponible(Joueur joueur) {
        return false;
    }

    public int getIdentifiant(){
        return identifiant;
    }
}
