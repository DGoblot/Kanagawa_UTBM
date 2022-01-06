package net.piagoblotguinot.vue;

import net.piagoblotguinot.controleur.Controleur;
import net.piagoblotguinot.controleur.Etats;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Ecran extends JFrame
{
    private Controleur controleur;
    private Etats etatCourant;
    private PanneauMenu panneauMenu;
    private PanneauNouvellePartie panneauNouvellePartie;
    private PanneauHistorique panneauHistorique;
    private PanneauJeu panneauJeu;

    public Ecran(Controleur controleur)
    {
        this.controleur = controleur;
        this.etatCourant = Etats.MENU;
        this.panneauMenu = new PanneauMenu(controleur);
        this.panneauNouvellePartie = new PanneauNouvellePartie(controleur);
        this.panneauHistorique = new PanneauHistorique(controleur);
        this.panneauJeu = new PanneauJeu(controleur);

        initialiser();
    }

    private void initialiser()
    {
        this.setTitle("UTGawa");
        //this.setSize(780,500);
        this.setSize(1910,1070);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        try
        {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        }
        catch (Exception e)
        {
            System.out.println("Problème avec le LookAndFeel");
            e.printStackTrace();
        };
        /*
        this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                int choix = JOptionPane.showConfirmDialog(Ecran.this,"Quitter le jeu ?", "Veuillez confirmer", JOptionPane.YES_NO_OPTION);
                    if(choix == JOptionPane.YES_OPTION)
                    {
                        System.exit(0);
                    }
            }
        });*/

        this.setContentPane(this.panneauJeu);
        this.setVisible(true);
    }

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

            case FIN_JEU-> {break;}
        }
    }

    /* GETTERS */
    public PanneauMenu getPanneauMenu() {return panneauMenu;}
    public PanneauNouvellePartie getPanneauNouvellePartie() {return panneauNouvellePartie;}
    public PanneauHistorique getPanneauHistorique() {return panneauHistorique;}
    public PanneauJeu getPanneauJeu() {return panneauJeu;}
}
