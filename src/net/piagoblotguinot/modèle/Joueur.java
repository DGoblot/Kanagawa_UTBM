package net.piagoblotguinot.modèle;

import net.piagoblotguinot.controleur.Main;

import java.util.*;

public class Joueur
{
    int score;
    int numeroAbsolu; //Numéro du joueur
    ArrayList<Carte> main;
    ArrayList<Uv> uvs;
    ArrayList<Competence> competences;
    ArrayList<Filiere> filieres;
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
    
    /*choix de passer son tour ou prendre des cartes*/
    
    public boolean choixAction(int hauteur, int joueursRestants, int indicePioche)
    {


        if (hauteur < 2 && joueursRestants > 1 && indicePioche < 72) { //Si le joueur a le droit de passer son tour
            return Main.controleur.getEvenements().getChoixJoueur();
        } else {
            return true;
        }
    }

    /*prendre les cartes de la colonne choisie*/
    public void prendreCarte(int colonne, Partie partie)
    {
        for (int i = 0; i <= partie.hauteur; i++) {
            if (partie.plateau[i][colonne] == null)
                continue;

            main.add(partie.plateau[i][colonne]);
            partie.plateau[i][colonne] = null;
        }

        partie.updateView();

        Main.controleur.getEvenements().afficherMain(this.main);
        tour(partie);

    }
    
    /*Boucle du choix des actions une fois les cartes prises*/
    
    private void tour(Partie partie) {

        boolean fini = false;
        int n;


        resetInventaire();


        while (!fini && main.size() != 0)
        {


            switch (Main.controleur.getEvenements().getChoixAction())
            {
                case 1: /* Placer comme uV */
                    if (!placerUv(Main.controleur.getEvenements().getChoixCarte(this.main.size()),partie)){
                        System.out.println("Choix invalide");
                    }
                    break;
                case 2: /* Placer comme compétence */
                    if (!placerCompetence(Main.controleur.getEvenements().getChoixCarte(this.main.size()),partie)){
                        System.out.println("Choix invalide");
                    }
                    break;
                case 3: /* Poser ordi */
                    if (!placerOrdi(Main.controleur.getEvenements().getChoixCarte(this.competences.size()))){
                        System.out.println("Choix invalide");
                    }
                    break;
                case 4: /* Bouger ordi */
                    n = Main.controleur.getEvenements().getChoixCarte(this.competences.size());
                    if(!bougerOrdinateur(n-1,Main.controleur.getEvenements().getChoixCarte(this.competences.size())-1))
                    {
                        System.out.println("Choix invalide");
                    }
                    break;
                case 5: /* Fin tour */
                    if (main.size() <= mains){
                        fini = true;
                    } else {
                        System.out.println("Main trop grande");
                    }
                    break;
                default:
                    System.out.println("Choix invalide");
            }

            partie.updateView();
            Main.controleur.getEvenements().afficherMain(this.main);
        }





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


        Main.controleur.getEvenements().ajouterCompetence(this.numeroAbsolu-1, main.get(indice-1).competence);


        main.remove(indice-1);
        
        testFiliere(partie);

        return true;

    }

    private boolean placerUv(int indice, Partie partie) {
        indice--;
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
        Main.controleur.getEvenements().ajouterUV(this.numeroAbsolu-1, main.get(indice).uv);

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
            Main.controleur.getEvenements().retirerFiliere(filiere.getIdentifiant());
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

    //Calcule la plus longue suite d'années dans les UV
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
