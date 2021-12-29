package net.piagoblotguinot.modèle;
import net.piagoblotguinot.controleur.Controleur;
import net.piagoblotguinot.controleur.Main;

import java.util.*;

public class Joueur
{
    double score;
    int numeroAbsolu;
    int numeroCourant;
    ArrayList<Carte> main;
    Uv[] uvs;
    Competence[] competences;
    Filiere[] filieres;
    int[] filieresRefusees;
    int mouvements;
    int ordinateurs;
    int saisonInitiale;
    int paysageInitial;

    public Joueur(int numero)
    {
        this.score = 0;
        this.numeroAbsolu = numero;
        //this.numeroCourant = numeroCourant;
        main = new ArrayList<>();
        this.uvs = new Uv[32];
        this.competences = new Competence[32];
        this.filieres = new Filiere[19];
        this.filieresRefusees = new int[19];

        this.mouvements = 1;
        this.ordinateurs = 2;
        //this.saisonInitiale = saisonInitiale;
        //this.paysageInitial = paysageInitial;
    }

    public boolean tour(int hauteur, int joueursRestants)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Tour du joueur " + numeroAbsolu);

        if (hauteur < 2 && joueursRestants > 1) {
            System.out.println("Prendre des cartes ?\n");
            return scanner.nextBoolean();
        } else {
            System.out.println("Prise de carte obligatoire");
            return true;
        }
    }

    public void prendreCarte(int colonne, Carte[][] plateau, int hauteur)
    {
        for (int i = 0; i <= hauteur; i++) {
            main.add(plateau[i][colonne]);
            plateau[i][colonne] = null;
        }

        System.out.println("Cartes prises :");

        affMain();

    }

    private void affMain() {

        Iterator<Carte> it = main.iterator();

        System.out.println();

        while (it.hasNext())
        {
            it.next().aff();
            System.out.println();
        }

        System.out.println();

    }

    public void poserCompetence()
    {

    }

    public void poserUV()
    {

    }

    public boolean conditionRemplie()
    {
        return false;
    }

    public void poserOrdinateur()
    {

    }

    public void bougerOrdinateur()
    {

    }

    public boolean diplomeDisponnible()
    {
        return true;
    }

    public void poserStage()
    {

    }
}
