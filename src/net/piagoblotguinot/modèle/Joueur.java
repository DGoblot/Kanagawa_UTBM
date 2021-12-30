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
    ArrayList<Uv> uvs;
    ArrayList<Competence> competences;
    ArrayList<Filiere> filieres;
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
        this.main = new ArrayList<>();
        this.uvs = new ArrayList<>();
        this.competences = new ArrayList<>();
        this.filieres = new ArrayList<>();
        this.filieresRefusees = new int[19];

        this.mouvements = 1;
        this.ordinateurs = 2;
        //this.saisonInitiale = saisonInitiale;
        //this.paysageInitial = paysageInitial;
    }

    public boolean choixAction(int hauteur, int joueursRestants)
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

        tour();



    }

    private void tour() {

        System.out.println("Tour du joueur");

        /*Scanner scanner = new Scanner(System.in);
        affJoueur();
        System.out.println();*/


    }

    private void affJoueur() {

        System.out.println("Liste des UV :");
        affUv();
        System.out.println("Liste des compétences :");
        affCompetences();
        System.out.println("Main du joueur :");
        affMain();




    }

    private void affCompetences() {

        Iterator<Competence> it = competences.iterator();

        System.out.println();

        while (it.hasNext())
        {
            it.next().aff(true);
            System.out.println();
        }

        System.out.println();
    }

    private void affUv() {

        Iterator<Uv> it = uvs.iterator();

        System.out.println();

        while (it.hasNext())
        {
            it.next().aff();
            System.out.println();
        }

        System.out.println();

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
