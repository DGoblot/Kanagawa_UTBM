package net.piagoblotguinot.modèle;

import net.piagoblotguinot.controleur.Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Partie
{
    private boolean running;

    /* Matériel de jeu */
    protected Carte[][] plateau;
    protected Carte[] pioche;
    protected Carte[] cartes;
    protected boolean[][] masque;
    protected int hauteur;
    protected int indicePioche;
    protected ArrayList<Filiere> filieres;

    /* Joueurs */
    private Joueur[] joueurs;
    private String[] nomsJoueurs;
    private int nbJoueurs;
    private int premierJoueur;
    private int assistant;
    private boolean[] aPrisCarte;
    private int[] scores;

    public Partie(int nbJoueurs, String[] nomsJoueurs)
    {
        this.running = true;
        this.plateau = new Carte[3][4];
        this.pioche = new Carte[72];
        this.cartes = new Carte[72];
        this.masque = new boolean[3][4];
        this.hauteur = 0;
        this.indicePioche = 0;
        this.filieres = new ArrayList<>();
        this.nbJoueurs = nbJoueurs;
        this.joueurs = new Joueur[4];
        this.nomsJoueurs = new String[4];

        for(int i = 0; i < nbJoueurs; i++)
        {
            this.nomsJoueurs[i] = nomsJoueurs[i];
            System.out.println(this.nomsJoueurs[i]);
        }
        System.out.println("terminé");

        this.premierJoueur = 0;
        this.assistant = 0;
        this.aPrisCarte = new boolean[4];
        for(int i = 0 ; i < this.nbJoueurs ; i++)
        {
            this.joueurs[i] = new Joueur(i+1);

            //Temporaire
            this.joueurs[i].anneeInitiale = "première";
            this.joueurs[i].competences.get(0).domaine = "reseau";
            //!Temporaire

            this.aPrisCarte[i] = false;
        }
        joueurs[0].assistant = true;
        joueurs[0].grandmaitre = true;
    }

    public void init() // OK
    {
        //remplir la pioche :
        // pour chaque case de pioche[i], lire dans le fichier la ligne[i]
        // afin de récuperer les données et les copier dans les attributs des classes, Carte, Uv et Compétence

        creerCartes(); // OK

        melangerPioche(); // OK

        creerFilieres(); // OK

        affFilieres(); // OK




        //Attribuer aléatoirement les saisons de départ aux joueurs.
        // Attribuer aléatoirement les numéros des joueurs et l'attribut Main::assistant

        //Affiche le plateau et la coniguration initiale du jeu
    }

    private void affFilieres() { // OK
        for (Filiere filiere : filieres) {
            filiere.aff();
        }
    }

    private void creerCartes() // OK
    {
        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("data/Cartes_UTGawa.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                this.cartes[i] = lireCarte(i,line);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void creerFilieres() // OK
    {
        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("data/Filieres_UTGawa.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                this.filieres.add(lireFiliere(i+1,line));
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Carte lireCarte(int i, String line) { // OK
        Carte retour = new Carte();
        String[] arr = line.split("\\s+");

        retour.competence.identifiant = i+1;
        retour.uv.identifiant = i+1;

        retour.competence.domaine = arr[0];
        retour.competence.objet = arr[1];
        retour.competence.ects = Integer.parseInt(arr[2]);

        retour.uv.domaine = arr[3];
        retour.uv.nombre = Integer.parseInt(arr[4]);
        retour.uv.annee = arr[5];
        retour.uv.ects = Integer.parseInt(arr[6]);
        retour.uv.approche = arr[7];
        retour.uv.type = arr[8];

        return retour;
    }

    private Filiere lireFiliere(int i, String line) { // OK
        String[] arr = line.split("\\s+");
        String[] elements = new String[Integer.parseInt(arr[5])];
        Filiere retour = null;
        switch (arr[0]) {
            case "identique" -> retour = new FiliereIdentique(arr[1], Integer.parseInt(arr[2]), Boolean.parseBoolean(arr[3]), arr[4], Integer.parseInt(arr[5]));
            case "différent" -> retour = new FiliereDifferent(arr[1], Integer.parseInt(arr[2]), Boolean.parseBoolean(arr[3]), arr[4], Integer.parseInt(arr[5]));
            case "enumération" -> {
                System.arraycopy(arr, 6, elements, 0, arr.length - 6);
                retour = new FiliereEnum(arr[1], Integer.parseInt(arr[2]), Boolean.parseBoolean(arr[3]), arr[4], elements);
            }
            default -> {
            }
        }

        retour.identifiant = i;

        return retour;
    }

    public void run()
    {

        while(this.running) // Boucle des tours de jeu.
        {
            resetTour();
            int i;

            System.out.println("Debut du tour");


            while (joueursRestants() > 0) {
                this.ajouterLigne();
                updateView();
                affPlateau();
                for (int j = this.premierJoueur; j < this.nbJoueurs + this.premierJoueur; j++) {
                    i = j % nbJoueurs;
                    if (!aPrisCarte[i]) {
                        if (joueurs[i].choixAction(hauteur, joueursRestants(), indicePioche))//Le joueur prend des cartes
                        {
                            //System.out.println();
                            //Scanner scanner = new Scanner(System.in);
                            //System.out.println("Quelle colonne ?\n");
                            joueurs[i].prendreCarte(Main.controleur.getEvenements().getChoixColonne(new boolean[4]), this);
                            //System.out.println("Colonne choisie");
                            aPrisCarte[i] = true;
                        }
                    }

                }
                hauteur++;
            }
            if(testFinJeu()) {this.running = false;}
        }

        for(int i = 0; i < nbJoueurs; i++)
        {

           //System.out.println("Score du joueur " + (i+1) + " :");
           scores[i] = joueurs[i].score;
           /*System.out.println("Total : " + score);
           System.out.println();*/

            Main.controleur.getEvenements().ecranFinDePartie();


        }

        // Afficher un écran présentant les résultats finaux.
        //
    }

    protected void updateView()
    {
        Main.controleur.getEvenements().updateView(this);
    }





    private void resetTour() {

        actualiserGrandMaitre();

        hauteur = 0;
        for (int i = 0; i < nbJoueurs; i++) {
            aPrisCarte[i] = false;

        }
    }

    private void actualiserGrandMaitre() {
        for (int i = 0; i < nbJoueurs; i++) {
                if (joueurs[i].assistant) {
                    joueurs[i].grandmaitre = true;
                    premierJoueur = i;
                } else {
                    joueurs[i].grandmaitre = false;
                }
            }

        }


    private int joueursRestants() {
        int n = 0;
        for (int i = 0; i < nbJoueurs; i++) {
            if (!aPrisCarte[i])
            {
                n++;
            }

        }
        return n;
    }

    private void affPlateau() {
        for (int i = 0; i < nbJoueurs; i++) {
            System.out.print("Colonne ");
            System.out.println(i+1);
            System.out.println();
            for (int j = 0; j <= hauteur; j++) {
                System.out.print("Ligne ");
                System.out.println(j+1);
                System.out.println();
                if (plateau[j][i] == null)
                {
                    System.out.println("Vide");
                } else {
                    plateau[j][i].aff();
                }
                System.out.println();


            }

        }
    }

    public void melangerPioche() // A refaire en plus simple
    {
        /*
        ArrayList<Carte> laPioche = new ArrayList<>();
        Collections.shuffle(laPioche); */

        int position;
        Random random = new Random();
        for (int i = 0; i < 72; i++) {
            position = random.nextInt(72);


            while(pioche[position] != null)
            {
                position++;
                if (position == 72)
                {
                    position = 0;
                }
            }
            pioche[position] = cartes[i];
        }
    }

    public void ajouterLigne()
    {
        if (hauteur == 0) {
            for (int i = 0; i < nbJoueurs; i++) {
                plateau[0][i] = pioche[indicePioche++];
                if (indicePioche > 71)
                    return;
            }
        } else {

            for (int i = 0; i < nbJoueurs; i++) {
                if (plateau[hauteur-1][i] != null)
                {
                    plateau[hauteur][i] = pioche[indicePioche++];
                    if (indicePioche > 71)
                        return;
                }
            }
        }
    }

    public boolean peutPasserSonTour()
    {
        return true;
    }

    public boolean testFinManche()
    {
        return false;
    }

    public void changePremierJoueur()
    {

    }

    public boolean testFinJeu()
    {
        if (indicePioche > 71)
            return true;
        for (int i = 0; i < nbJoueurs; i++) {
            if (joueurs[i].uvs.size() > 10)
                return true;
        }
        return false;
    }

    double calculScore(int numJoueur)
    {
        return 5000;
    }

    public void donnerAssistant(int i) {
        for (int j = 0; j <nbJoueurs; j++) {
            joueurs[j].assistant = false;
        }
        joueurs[i].assistant = true;
    }

    public boolean isRunning() {
        return running;
    }

    public Carte[][] getPlateau() {
        return plateau;
    }

    public Carte[] getPioche() {
        return pioche;
    }

    public Carte[] getCartes() {
        return cartes;
    }

    public boolean[][] getMasque() {
        return masque;
    }

    public int getHauteur() {
        return hauteur;
    }

    public int getIndicePioche() {
        return indicePioche;
    }

    public ArrayList<Filiere> getFilieres() {
        return filieres;
    }

    public Joueur[] getJoueurs() {
        return joueurs;
    }

    public String[] getNomsJoueurs() {
        return nomsJoueurs;
    }

    public int getNbJoueurs() {
        return nbJoueurs;
    }

    public int getPremierJoueur() {
        return premierJoueur;
    }

    public int getAssistant() {
        return assistant;
    }

    public boolean[] getaPrisCarte() {
        return aPrisCarte;
    }

    public int[] getScores(){
        return scores;
    }
}
