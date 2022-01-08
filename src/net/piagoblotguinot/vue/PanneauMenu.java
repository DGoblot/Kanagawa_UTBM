package net.piagoblotguinot.vue;

import net.piagoblotguinot.controleur.Controleur;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
/*
        Classe gérant l'affichage du menu principal
*/
public class PanneauMenu extends JPanel
{
    Controleur controleur;
    Dimension dimensionBouton;
    JLabel titreJeu;
    JButton boutonNouvellePartie;
    JButton boutonHistorique;
    JButton boutonCredits;
    JButton boutonQuitter;

    public PanneauMenu(Controleur controleur)
    {
        this.controleur = controleur;
        this.dimensionBouton = new Dimension(170,30);
        this.titreJeu = creerTitreJeu();
        this.boutonNouvellePartie = creerBoutonNouvellePartie();
        this.boutonHistorique = creerBoutonHistorique();
        this.boutonCredits = creerBoutonCredits();
        this.boutonQuitter = creerBoutonQuitter();

        initialiser();
    }

    private void initialiser()
    {
        this.setBackground(new Color(255, 255, 255));
        this.setLayout(null);
        this.add(this.titreJeu);
        this.add(this.boutonNouvellePartie);
        this.add(this.boutonHistorique);
        this.add(this.boutonCredits);
        this.add(this.boutonQuitter);
    }

    private JLabel creerTitreJeu()
    {
        JLabel label = new JLabel();
        label.setFont(new Font("Source Code Pro", Font.BOLD, 48));
        label.setSize(new Dimension(600,400));
        label.setLocation(100,0);
        label.setForeground(Color.white);

        try
        {
            BufferedImage original = ImageIO.read(new File("data/Logo.png"));
            Image image = original.getScaledInstance((1024/3),(734/3),Image.SCALE_SMOOTH);
            ImageIcon icone = new ImageIcon(image);

            label.setIcon(icone);
        }
        catch (Exception e){}


        return label;
    }

    private JButton creerBoutonNouvellePartie()
    {
        JButton button = new JButton("Nouvelle partie");
        button.setForeground(new Color(2, 2, 12));
        button.setSize(dimensionBouton);
        button.setLocation(575,120);
        button.setBackground(Color.white);
        button.addActionListener(e -> this.controleur.getEvenements().nouvellePartie());
        return button;
    }

    private JButton creerBoutonHistorique()
    {
        JButton button = new JButton("Historique des parties");
        button.setSize(dimensionBouton);
        button.setLocation(575,160);
        button.setBackground(Color.white);
        button.setForeground(new Color(2, 2, 12));
        button.addActionListener(e -> this.controleur.getEvenements().historique());
        return button;
    }

    private JButton creerBoutonCredits()
    {
        JButton button = new JButton("Crédits");
        button.setSize(dimensionBouton);
        button.setLocation(575,200);
        button.setBackground(Color.white);
        button.setForeground(new Color(2, 2, 12));
        button.addActionListener(e -> this.controleur.getEvenements().credits());
        return button;
    }

    private JButton creerBoutonQuitter()
    {
        JButton button = new JButton("Quitter le jeu");
        button.setSize(dimensionBouton);
        button.setLocation(575,240);
        button.setBackground(Color.white);
        button.setForeground(new Color(2, 2, 12));
        button.addActionListener(e -> this.controleur.getEvenements().quitter());
        return button;
    }
    /*
        Affichage des noms des auteurs du programme
    */
    public void afficherCredits()
    {
        JOptionPane.showMessageDialog(new JFrame(),"Réalisé par :\nDavid GOBLOT\nUgo PIA\nJoshua GUINOT","Credits",1,null);
    }

}
