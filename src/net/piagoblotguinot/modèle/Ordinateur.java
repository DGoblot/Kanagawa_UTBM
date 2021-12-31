package net.piagoblotguinot.modèle;

public class Ordinateur {

    boolean actif;

    public Ordinateur(){
        actif = true;
    }

    public void reset() {
        actif = true;
    }

    public void use(){
        actif = false;
    }
}
