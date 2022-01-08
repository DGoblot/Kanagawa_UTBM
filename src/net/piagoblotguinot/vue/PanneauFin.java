package net.piagoblotguinot.vue;

import net.piagoblotguinot.controleur.Controleur;

import javax.swing.*;
import java.awt.*;

public class PanneauFin extends JPanel
{
    Controleur controleur;
    JLabel labelTitre;
    Bouton boutonRetour;
    JLabel resultats;

    PanneauFin(Controleur controleur)
    {
        this.controleur = controleur;
        this.labelTitre = creerLabelTitre();
        this.boutonRetour = creerBoutonRetour();
        this.resultats = creerLabelResultats();
        initialiser();
    }

    private JLabel creerLabelResultats(){
        int[] scores = controleur.getScores();
        String[] noms = controleur.getNoms();

        String resultats = "Scores :\n";
        for(int i = 0 ; i <this.controleur.getnbJoueurs();i++)
        {
            resultats += noms[i] + " " + scores[i] + "\n";
        }

        JLabel label = new JLabel("Resultats");
        label.setFont(new Font("Source Code Pro", Font.BOLD, 24));
        label.setSize(new Dimension(300,30));
        label.setLocation(30,10);
        return label;

    }

    private void initialiser()
    {
        this.setBackground(new Color(45, 45, 45));
        this.setLayout(null);
        this.add(labelTitre);
        this.add(boutonRetour);
        this.add(this.resultats);
    }

    private JLabel creerLabelTitre()
    {
        JLabel label = new JLabel("Resultats");
        label.setFont(new Font("Source Code Pro", Font.BOLD, 24));
        label.setSize(new Dimension(300,30));
        label.setLocation(30,10);
        return label;
    }

    private Bouton creerBoutonRetour()
    {
        Bouton bouton = new Bouton("Retour");
        bouton.setLocation(650,400);
        bouton.addActionListener(e -> this.controleur.getEvenements().retourMenuPrincipal());
        return bouton;
    }


}
