package net.piagoblotguinot.modèle;

public class Filiere
{
    int identifiant; //Numéro de la filière
    
    boolean[] skip; //Liste des joueurs ayant renoncé à cette filière
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
    
    /*Teste si le joueur peut prendre cette filière*/
    protected boolean disponible(Joueur joueur) {
        return false;
    }

    public int getIdentifiant(){
        return identifiant;
    }
}
