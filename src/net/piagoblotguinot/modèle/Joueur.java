package net.piagoblotguinot.modèle;

import net.piagoblotguinot.controleur.Controleur;
import net.piagoblotguinot.controleur.Main;

import java.util.*;

public class Joueur
{
    int score;
    int numeroAbsolu;
    int numeroCourant;
    ArrayList<Carte> main;
    ArrayList<Uv> uvs;
    ArrayList<Competence> competences;
    ArrayList<Filiere> filieres;
    int[] filieresRefusees;
    int mains;
    int mouvements;
    int stages;
    int ordinateurs;
    boolean assistant;
    boolean grandmaitre;
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
        this.stages = 0;
        this.ordinateurs = 2;
        this.mains = 0;

        assistant = false;
        grandmaitre = false;

        competences.add(new Competence());

    }

    public boolean choixAction(int hauteur, int joueursRestants, int indicePioche)
    {


        Scanner scanner = new Scanner(System.in);

        //System.out.println("Tour du joueur " + numeroAbsolu);

        if (hauteur < 2 && joueursRestants > 1 && indicePioche < 72) {
            System.out.println("Prendre des cartes ?\n");
            return Main.controleur.getEvenements().getChoixJoueur();
        } else {
            System.out.println("Prise de carte obligatoire");
            return true;
        }
    }

    public void prendreCarte(int colonne, Partie partie)
    {
        for (int i = 0; i <= partie.hauteur; i++) {
            if (partie.plateau[i][colonne] == null)
                continue;

            main.add(partie.plateau[i][colonne]);
            partie.plateau[i][colonne] = null;
        }
        Main.controleur.updatePlateau(partie.plateau);
        tour(partie);

    }

    private void tour(Partie partie) {

        Scanner scanner = new Scanner(System.in);
        boolean fini = false;
        int n;

        System.out.println("Tour du joueur " + numeroAbsolu);

        resetInventaire();


        while (!fini && main.size() != 0)
        {
            Main.controleur.afficherPanneauAction(); // Nom temporaire

            //affJoueur();
            //System.out.println();
            /*
            System.out.println("Choix action :");
            System.out.println("1: Placer UV");
            System.out.println("2: Placer compétence");
            System.out.println("3: Placer ordinateur");
            System.out.println("4: Deplacer ordinateur");
            System.out.println("5: Finir tour");*/
            //switch (scanner.nextInt()) {
            switch (Main.controleur.getEvenements().getChoixAction())
            {
                case 1: /* Placer comme uV */
                    //System.out.println("Choix de la carte :");
                    if (!placerUv(Main.controleur.getEvenements().getChoixCarte(this.main.size()),partie)){
                        System.out.println("Choix invalide");
                    }
                    break;
                case 2: /* Placer comme compétence */
                    //System.out.println("Choix de la carte :");
                    if (!placerCompetence(Main.controleur.getEvenements().getChoixCarte(this.main.size()),partie)){
                        System.out.println("Choix invalide");
                    }
                    break;
                case 3: /* Poser ordi */
                    System.out.println("Choix de la carte :");
                    if (!placerOrdi(Main.controleur.getEvenements().getChoixCarte(this.competences.size()))){
                        System.out.println("Choix invalide");
                    }
                    break;
                case 4: /* Bouger ordi */
                    System.out.println("Choix de la case de depart :");
                    n = Main.controleur.getEvenements().getChoixCarte(this.competences.size());
                    System.out.println("Choix de la case d'arrivée :");
                    if(!bougerOrdinateur(n-1,Main.controleur.getEvenements().getChoixCarte(this.competences.size())-1))
                    {
                        System.out.println("Choix invalide");
                    }
                    break;
                case 5: /* Fin tour */
                    if (main.size() <= mains){
                        fini = true;
                    } else {
                        //System.out.println("Main trop grande");
                    }
                    break;
                default:
                    //System.out.println("Choix invalide");
            }
        }

        //System.out.println("Fin du tour");




    }

    private boolean placerOrdi(int indice) {
        if (ordinateurs == 0 || indice > competences.size() || competences.get(indice-1).ordi.exists || Objects.equals(competences.get(indice - 1).domaine, "aucun")) {
            return false;
        }

        ordinateurs--;
        competences.get(indice-1).ordi.create();

        return true;
    }

    private boolean placerCompetence(int indice, Partie partie) {
        if(indice>main.size()) {
            return false;
        }
        competences.add(main.get(indice-1).competence);


        switch (main.get(indice - 1).competence.objet) {
            case "main" -> mains++;
            case "fleche" -> mouvements++;
            case "proftp" -> {
                assistant = true;
                partie.donnerAssistant(numeroAbsolu - 1);
            }
            case "ordi" -> ordinateurs++;
            default -> {
            }
        }


        main.remove(indice-1);
        
        testFiliere(partie);

        return true;

    }

    private boolean placerUv(int indice, Partie partie) {
        int domainesDispo = 0;
        int domainesRequis = main.get(indice).uv.nombre;

        for (Competence competence : competences) {
            if (competence.ordi.actif && (Objects.equals(competence.domaine, main.get(indice).uv.domaine) || Objects.equals(competence.domaine, "algo")) && competence.ordi.exists) {
                domainesDispo++;
            }
        }

        if (domainesDispo < domainesRequis) {
            return false;
        }

        for (int i = 0; i < competences.size() && domainesRequis>0; i++) {
            if (Objects.equals(competences.get(i).domaine, main.get(indice).uv.domaine) && competences.get(i).ordi.exists && competences.get(i).ordi.actif) {
                domainesRequis--;
                competences.get(i).ordi.use();
            }

        }


        for (int i = 0; i < competences.size() && domainesRequis>0; i++) {
            if (Objects.equals(competences.get(i).domaine, "algo") && competences.get(i).ordi.exists && competences.get(i).ordi.actif) {
                domainesRequis--;
                competences.get(i).ordi.use();
            }

        }

        uvs.add(main.get(indice).uv);
        main.remove(indice);
        
        testFiliere(partie);

        return true;

    }

    private void testFiliere(Partie partie)
    {

        Scanner scanner = new Scanner(System.in);
        ArrayList<Filiere> prises = new ArrayList<>();
        boolean filierePrise = false;
        for (Filiere filiere : partie.filieres) {
            if (filiere.disponible(this)){
                System.out.println("Filière disponible :");
                filiere.aff();

                System.out.print("Prendre ? ");
                if (Main.controleur.getEvenements().getChoixFiliere(filiere)){
                    prendreFiliere(filiere);
                    prises.add(filiere);
                    filierePrise = true;
                } else {
                    filiere.skip[numeroAbsolu-1] = true;
                }

            }

        }
        for (Filiere filiere : prises) {
            partie.filieres.remove(filiere);
        }
        if (filierePrise)
            testFiliere(partie);
    }

    private void prendreFiliere(Filiere filiere) {
        filieres.add(filiere);
        switch (filiere.objet){
            case "ordi" -> ordinateurs++;
            case "proftp" -> assistant = true;
        }
        if (filiere.stage)
            stages++;
    }

    private void resetInventaire() {
        mouvements = compterObjets("fleche") + 1;

        for (Competence competence : competences) {
            if (competence.ordi.exists) {
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

        System.out.println("Liste des filières :");
        affFilieres();
        System.out.println("Liste des UV :");
        affUv();
        System.out.println("Liste des compétences :");
        affCompetences();
        System.out.println("Main du joueur :");
        affMain();
        System.out.println("Inventaire : ");
        affInventaire();




    }

    private void affFilieres() {
        System.out.println();
        filieres.forEach(Filiere::aff);
        System.out.println();
    }

    private void affInventaire() {
        System.out.println("Ordinateurs restants : " + ordinateurs);
        System.out.println("Nombre de mains : " + mains);
        System.out.println("Mouvements restants : " + mouvements);
        System.out.println("Prof TP : " + assistant);
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
        if (!competences.get(depart).ordi.exists || competences.get(arrivee).ordi.exists || mouvements == 0)
        {
            return false;
        }

        competences.get(arrivee).ordi.create();
        competences.get(arrivee).ordi.actif = competences.get(depart).ordi.actif;
        competences.get(depart).ordi.destroy();

        System.out.println("Etat ordi depart : "+competences.get(depart).ordi.exists);
        System.out.println("Etat ordi arrivee : "+competences.get(arrivee).ordi.exists);

        return true;

    }

    public boolean diplomeDisponnible()
    {
        return true;
    }

    public void poserStage()
    {

    }

    public int score(){

        int total = 0;
        for (Uv uv : uvs) {
            total += uv.ects;
        }

        System.out.println("Total credits UV : " + total);

        score += total;
        total = 0;


        total += uvs.size() + 1; //+1 pour la tuile de base

        for (Competence competence : competences) {
            total += competence.ects;
        }


        System.out.println("Total credits compétences : " + total);

        score += total;
        total = 0;


        for (Filiere filiere : filieres) {
            total += filiere.ects;
        }



        System.out.println("Total credits filières : " + total);

        score += total;
        total = 0;


        if (grandmaitre)
            total += 2;


        System.out.println("Credits grand maitre : " + total);

        score += total;
        total = 0;


        total += suiteAnnees();



        System.out.println("Plus grande suite d'années : " + total);

        score += total;


        return score;
        }

    private int suiteAnnees() {

        int[] taille = new int[4];
        String[] annees = {"première", "deuxième", "troisième", "quatrième"};
        for (int i = 0; i < annees.length; i++) {
            if (Objects.equals(anneeInitiale, annees[i]))
                taille[i] = 1;
            for (Uv uv : uvs) {
                if (Objects.equals(uv.annee, annees[i]) || Objects.equals(uv.annee, "stage"))
                    taille[i]++;
                else
                    taille[i] = 0;

            }

            
        }
        return max(taille);
    }

    private int max(int[] taille) {
        int max = 0;
        for (int j : taille) {
            if (max < j)
                max = j;

        }
        return max;
    }

}
