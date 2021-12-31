package net.piagoblotguinot.modèle;

public class Ordinateur {

    boolean actif;
    boolean exists;

    public Ordinateur(){
        exists = false;
        actif = true;
    }

    public void reset() {
        actif = true;
    }

    public void use(){
        actif = false;
    }

    public void create() {
        exists = true;
        actif = true;
    }

    public void destroy() {
        exists = false;
    }
}
