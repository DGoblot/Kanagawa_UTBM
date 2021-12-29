package net.piagoblotguinot.vue;

import net.piagoblotguinot.controleur.Controleur;

import javax.swing.*;
import java.awt.*;

public class PanneauHistorique extends JPanel
{
    Controleur controleur;
    Dimension dimensionBouton;
    JLabel labelTitre;
    JButton boutonRetour;

    PanneauHistorique(Controleur controleur)
    {
        this.controleur = controleur;
        this.dimensionBouton = new Dimension(80,30);
        this.labelTitre = creerLabelTitre();
        this.boutonRetour = creerBoutonRetour();

        initialiser();
    }

    private void initialiser()
    {
        this.setBackground(new Color(2, 2, 12));
        this.setLayout(null);
        this.add(labelTitre);
        this.add(boutonRetour);
    }

    private JLabel creerLabelTitre()
    {
        JLabel label = new JLabel("Historiques des parties");
        label.setFont(new Font("Source Code Pro", Font.BOLD, 24));
        label.setSize(new Dimension(300,30));
        label.setLocation(30,10);
        return label;
    }

    private JButton creerBoutonRetour()
    {
        JButton button = new JButton("Retour");
        button.setForeground(new Color(2, 2, 12));
        button.setSize(this.dimensionBouton);
        button.setLocation(650,400);
        button.setBackground(Color.white);
        button.addActionListener(e -> this.controleur.getEvenements().retourMenuPrincipal());
        return button;
    }
}
