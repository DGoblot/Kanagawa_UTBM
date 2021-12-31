package net.piagoblotguinot.modèle;

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
        this.mains = 0;

        competences.add(new Competence());

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
        int n;

        System.out.println("Tour du joueur " + numeroAbsolu);

        resetInventaire();


        while (!fini && main.size() != 0) {


            affJoueur();
            System.out.println();

            System.out.println("Choix action :");
            System.out.println("1: Placer UV");
            System.out.println("2: Placer compétence");
            System.out.println("3: Placer ordinateur");
            System.out.println("4: Deplacer ordinateur");
            System.out.println("5: Finir tour");
            switch (scanner.nextInt()) {
                case 1:
                    System.out.println("Choix de la carte :");
                    if (!placerUv(scanner.nextInt()-1)){
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
                    System.out.println("Choix de la case de depart :");
                    n = scanner.nextInt();
                    System.out.println("Choix de la case d'arrivée :");
                    if(!bougerOrdinateur(n-1,scanner.nextInt()-1))
                    {
                        System.out.println("Choix invalide");
                    }
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
        if (ordinateurs == 0 || indice > competences.size() || competences.get(indice-1).ordi != null || Objects.equals(competences.get(indice - 1).domaine, "aucun")) {
            return false;
        }

        ordinateurs--;
        competences.get(indice-1).ordi = new Ordinateur();

        return true;
    }

    private boolean placerCompetence(int indice) {
        if(indice>main.size()) {
            return false;
        }
        competences.add(main.get(indice-1).competence);


        switch (main.get(indice - 1).competence.objet) {
            case "main" -> mains++;
            case "fleche" -> mouvements++;
            case "assistant" -> assistant = true;
            case "ordi" -> ordinateurs++;
            default -> {
            }
        }

        main.remove(indice-1);

        return true;

    }

    private boolean placerUv(int indice) {
        int domainesDispo = 0;
        int domainesRequis = Integer.parseInt(main.get(indice).uv.nombre);

        for (Competence competence : competences) {
            if (competence.ordi.actif && (Objects.equals(competence.domaine, main.get(indice).uv.domaine) || Objects.equals(competence.domaine, "algo")) && competence.ordi != null) {
                domainesDispo++;
            }
        }

        if (domainesDispo < domainesRequis) {
            return false;
        }

        for (int i = 0; i < competences.size() && domainesRequis>0; i++) {
            if (Objects.equals(competences.get(i).domaine, main.get(indice).uv.domaine) && competences.get(i).ordi != null && competences.get(i).ordi.actif) {
                domainesRequis--;
                competences.get(i).ordi.use();
            }

        }


        for (int i = 0; i < competences.size() && domainesRequis>0; i++) {
            if (Objects.equals(competences.get(i).domaine, "algo") && competences.get(i).ordi != null && competences.get(i).ordi.actif) {
                domainesRequis--;
                competences.get(i).ordi.actif = false;
            }

        }

        uvs.add(main.get(indice).uv);
        main.remove(indice);

        return true;

    }

    private void resetInventaire() {
        mouvements = compterObjets("fleche") + 1;

        for (Competence competence : competences) {
            if (competence.ordi != null) {
                competence.ordi.reset();
            }
        }

    }

    private int compterObjets(String objet) {

        int n = 0;

        for (Competence competence : competences) {
            if (Objects.equals(competence.objet, objet)) {
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

    public boolean conditionRemplie()
    {
        return false;
    }

    public boolean bougerOrdinateur(int depart, int arrivee)
    {
        if (competences.get(depart).ordi == null || competences.get(arrivee).ordi != null || mouvements == 0)
        {
            return false;
        }

        competences.get(arrivee).ordi = competences.get(depart).ordi;
        competences.get(depart).ordi = null;

        return true;

    }

    public boolean diplomeDisponnible()
    {
        return true;
    }

    public void poserStage()
    {

    }
}
