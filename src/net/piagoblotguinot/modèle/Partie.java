package net.piagoblotguinot.modèle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Partie
{
    private boolean running;

    /* Matériel de jeu */
    private Carte[][] plateau;
    private Carte[] pioche;
    private Carte[] cartes;
    private int[][] masque;
    private Filiere[] filieresDisponnibles;

    /* Joueurs */
    private Joueur[] joueurs;
    private String[] nomsJoueurs;
    private int nbJoueurs;
    private int premierJoueur;
    private int assistant;
    private boolean[] aPrisCarte;

    public Partie(int nbJoueurs, String[] nomsJoueurs)
    {
        this.running = true;
        this.plateau = new Carte[3][4];
        this.pioche = new Carte[72];
        this.cartes = new Carte[72];
        this.masque = new int[3][4];
        this.filieresDisponnibles = new Filiere[19];
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
            this.joueurs[i] = new Joueur();
            this.aPrisCarte[i] = false;
        }
    }

    public void init()
    {
        //remplir la pioche :
        // pour chaque case de pioche[i], lire dans le fichier la ligne[i]
        // afin de récuperer les données et les copier dans les attributs des classes, Carte, Uv et Compétence

        creerCartes();

        melangerPioche();

        for (int i = 0; i < 72; i++) {
            System.out.println("Nouvelle carte :");
            pioche[i].aff();
        }

        //Attribuer aléatoirement les saisons de départ aux joueurs.
        // Attribuer aléatoirement les numéros des joueurs et l'attribut Main::assistant

        //Affiche le plateau et la coniguration initiale du jeu
    }

    private void creerCartes()
    {
        int i = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("data/Cartes.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                this.cartes[i] = lireCarte(line);
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Carte lireCarte(String line) {
        Carte retour = new Carte();
        String[] arr = line.split("\\s+");

        retour.competence.domaine = arr[0];
        retour.competence.objet = arr[1];
        retour.competence.ects = arr[2];

        retour.uv.domaine = arr[3];
        retour.uv.nombre = arr[4];
        retour.uv.annee = arr[5];
        retour.uv.ects = arr[6];
        retour.uv.approche = arr[7];
        retour.uv.type = arr[8];

        return retour;
    }

    public void run()
    {
        System.out.println("Test");
        while(this.running) // Boucle des tours de jeu.
        {
            this.ajouterLigne();
            for(int i = this.premierJoueur ; i < this.nbJoueurs+this.premierJoueur ; i++)
            {
                if(joueurs[i % this.nbJoueurs].tour())//Le joueur prend des cartes
                {
                    System.out.println();
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Quelle colonne ?\n");
                    joueurs[i].prendreCarte(scanner.nextInt());
                }

            }

            if(testFinJeu()) {this.running = false;}
        }

        for(int i = 0; i < nbJoueurs; i++)
        {
            joueurs[i].score = calculScore(i);
        }

        // Afficher un écran présentant les résultats finaux.
        //
    }

    public void melangerPioche()
    {
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
        return false;
    }

    double calculScore(int numJoueur)
    {
        return 5000;
    }
}
