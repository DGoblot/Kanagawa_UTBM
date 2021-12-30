package net.piagoblotguinot.modèle;

import javax.swing.*;
import java.awt.*;
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
    int mains;
    int mouvements;
    int ordinateurs;
    boolean assistant;
    String anneeInitiale;
    String paysageInitial;

    public Joueur(int numero)
    {
        this.score = 0;
        this.numeroAbsolu = numero;
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

        Scanner scanner = new Scanner(System.in);
        boolean fini = false;

        System.out.println("Tour du joueur " + numeroAbsolu);

        resetInventaire();

        affJoueur();
        System.out.println();

        while (!fini && main.size() != 0) {

            System.out.println("Choix action :");
            System.out.println("1: Placer UV");
            System.out.println("2: Placer compétence");
            System.out.println("3: Placer ordinateur");
            System.out.println("4: Deplacer ordinateur");
            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("Choix de la carte :");
                    if (!placerUv(scanner.nextInt())){
                        System.out.println("Choix invalide");
                    }
                    break;
                case 2:
                    System.out.println("Choix de la carte :");
                    if (!placerCompetence(scanner.nextInt())){
                        System.out.println("Choix invalide");
                    }
                    break;
                case 3:
                    System.out.println("Choix de la carte :");
                    if (!placerOrdi(scanner.nextInt())){
                        System.out.println("Choix invalide");
                    }
                    break;
                case 4:
                    break;
                case 5:
                    if (main.size() <= mains){
                        fini = true;
                    } else {
                        System.out.println("Main trop grande");
                    }
                    break;
                default:
                    System.out.println("Choix invalide");
            }
        }

        System.out.println("Fin du tour");




    }

    private boolean placerOrdi(int indice) {
        return false;
    }

    private boolean placerCompetence(int indice) {
        if(indice>main.size()) {
            return false;
        }
        competences.add(main.get(indice-1).competence);
        main.remove(indice-1);
        return true;

    }

    private boolean placerUv(int indice) {
        return false;
    }

    private void resetInventaire() {
        mouvements = compterObjets("fleche") + 1;
        mains = compterObjets("main");

        competences.forEach(competence -> competence.actif = true);

    }

    private int compterObjets(String objet) {

        int n = 0;
        Iterator<Competence> it = competences.iterator();

        while (it.hasNext())
        {
            if(Objects.equals(it.next().objet, objet))
            {
                n++;
            }
        }

        return n;
    }

    private void affJoueur() {

        System.out.println("Liste des UV :");
        affUv();
        System.out.println("Liste des compétences :");
        affCompetences();
        System.out.println("Main du joueur :");
        affMain();
        System.out.println("Inventaire : ");
        affInventaire();




    }

    private void affInventaire() {
        System.out.println("Ordinateurs restants : " + ordinateurs);
        System.out.println("Nombre de mains : " + mains);
        System.out.println("Mouvements restants : " + mouvements);
        System.out.println("Assistant : " + assistant);
        System.out.println();
    }

    private void affCompetences() {

        System.out.println();
        competences.forEach(comp -> comp.aff(true));
        System.out.println();
    }

    private void affUv() {


        System.out.println();
        System.out.println("Annee initiale : " + anneeInitiale);
        System.out.println();
        uvs.forEach(Uv::aff);
        System.out.println();

    }

    private void affMain() {

        System.out.println();
        main.forEach(Carte::aff);
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
