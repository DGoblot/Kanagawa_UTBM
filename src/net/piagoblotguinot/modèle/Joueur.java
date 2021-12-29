package net.piagoblotguinot.modèle;
import java.util.*;

public class Joueur
{
    double score;
    int numeroAbsolu;
    int numeroCourant;
    Carte[] main;
    Uv[] uvs;
    Competence[] competences;
    Filiere[] filieres;
    int[] filieresRefusees;
    int mouvements;
    int ordinateurs;
    int saisonInitiale;
    int paysageInitial;

    public Joueur()
    {
        this.score = 0;
        //this.numeroAbsolu = numeroAbsolu;
        //this.numeroCourant = numeroCourant;
        this.main = new Carte[7];
        this.uvs = new Uv[32];
        this.competences = new Competence[32];
        this.filieres = new Filiere[19];
        this.filieresRefusees = new int[19];

        this.mouvements = 1;
        this.ordinateurs = 2;
        //this.saisonInitiale = saisonInitiale;
        //this.paysageInitial = paysageInitial;
    }

    public boolean tour()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Prendre des cartes ?\n");
        return scanner.nextBoolean();
    }

    public void prendreCarte(int colonne)
    {

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
