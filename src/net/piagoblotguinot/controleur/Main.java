package net.piagoblotguinot.controleur;

import javax.swing.*;

public class Main
{
    public static Controleur controleur;

    public static void main(String[] args)
    {
        controleur = new Controleur();
        controleur.init();
        //controleur.run();
    }

}
