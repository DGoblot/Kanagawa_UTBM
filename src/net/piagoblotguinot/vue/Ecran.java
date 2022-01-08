package net.piagoblotguinot.vue;

import net.piagoblotguinot.controleur.Controleur;
import net.piagoblotguinot.controleur.Etats;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;

/*
    Fenêtre du jeu
*/
public class Ecran extends JFrame
{
    private Controleur controleur;
    private Etats etatCourant;
    private PanneauMenu panneauMenu;
    private PanneauNouvellePartie panneauNouvellePartie;
    private PanneauHistorique panneauHistorique;
    private PanneauJeu panneauJeu;
    private PanneauFin panneauFin;

    public Ecran(Controleur controleur)
    {
        this.controleur = controleur;
        this.etatCourant = Etats.MENU;
        this.panneauMenu = new PanneauMenu(controleur);
        this.panneauNouvellePartie = new PanneauNouvellePartie(controleur);
        this.panneauHistorique = new PanneauHistorique(controleur);
        //this.panneauJeu = new PanneauJeu(controleur);*

        initialiser();
    }

    private void initialiser() {
        this.setTitle("UTGawa");
        try
        {
            BufferedImage original = ImageIO.read(new File("data/Icone.png"));
            ImageIcon icone = new ImageIcon(original);
            this.setIconImage(original);
        }
        catch (Exception e){}

        //this.setSize(780,500);
        this.setSize(1910, 1070);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (Exception e) {
            System.out.println("Problème avec le LookAndFeel");
            e.printStackTrace();
        }

        this.setContentPane(this.panneauMenu);
        this.setVisible(true);
    }

    /*
        Change l'état du jeu : change le panneau affiché dans la fenêtre
    */
    public void changerEtat(Etats nouvelEtat)
    {
        this.etatCourant = nouvelEtat;
        switch(etatCourant)
        {
            case MENU -> {this.setContentPane(this.panneauMenu); this.revalidate(); this.repaint(); break;}
            case HISTORIQUE -> {this.setContentPane(this.panneauHistorique); this.revalidate(); this.repaint();break;}
            case NOUVELLE_PARTIE -> {this.setContentPane(this.panneauNouvellePartie); this.revalidate(); this.repaint();break;}
            case PARTIE_EN_COURS ->
                    {
                        this.setSize(1910,1070);
                        this.setContentPane(this.panneauJeu);
                        this.setLocationRelativeTo(null);
                        this.revalidate();
                        this.repaint();
                        break;
                    }

            case FIN_JEU-> {
                this.panneauFin = new PanneauFin(this.controleur);
                this.setContentPane(this.panneauFin);
            }
        }
    }

    /* GETTERS */
    public PanneauMenu getPanneauMenu() {return panneauMenu;}
    public PanneauNouvellePartie getPanneauNouvellePartie() {return panneauNouvellePartie;}
    public PanneauHistorique getPanneauHistorique() {return panneauHistorique;}
    public PanneauJeu getPanneauJeu() {return panneauJeu;}

    public void setPanneauJeu(PanneauJeu panneauJeu) {this.panneauJeu = panneauJeu;}
}
