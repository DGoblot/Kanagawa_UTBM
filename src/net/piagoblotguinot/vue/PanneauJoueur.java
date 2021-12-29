package net.piagoblotguinot.vue;

import net.piagoblotguinot.controleur.Controleur;
import net.piagoblotguinot.utilitaires.Utilitaires;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class PanneauJoueur extends JPanel
{
    private Controleur controleur;
    private Point emplacement;
    private JPanel uvs, competences, informations;
    private final int LARGEUR = 480, HAUTEUR = 400; // Dimensions du panneau joueur

    public PanneauJoueur(Controleur controleur, Point emplacement)
    {
        this.controleur = controleur;
        this.emplacement = emplacement;
        this.uvs = creerUvs();
        this.competences = creerCompetences();
        this.informations = creerInformations();

        initialiser();
    }
    public void initialiser()
    {
        this.setLayout(null);
        this.setBounds(emplacement.x,emplacement.y,this.LARGEUR,this.HAUTEUR);
        this.setBackground(new Color(220, 15, 157));
        this.add(this.uvs);
        this.add(this.competences);
        this.add(this.informations);
    }


    private JPanel creerUvs()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBounds(this.getX(),this.getY(),this.LARGEUR,263);
        panel.setBackground(new Color(210, 148, 1));
        for(int i = 1; i <13;i++)
        {
            try
            {
                BufferedImage original = ImageIO.read(new File("data/Carte_"+ i +".png"));
                Image image = original.getScaledInstance(114/*(1024/9)*/,82/*(734/9)*/,Image.SCALE_DEFAULT);

                ImageIcon icone = new ImageIcon(image);
                JLabel label = new JLabel(icone);
                panel.add(label);
            } catch(Exception e){e.printStackTrace();}
        }
        return panel;
    }

    private JPanel creerCompetences()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBounds(this.getX(), this.getY()+this.uvs.getHeight(), this.LARGEUR, 90);
        panel.setBackground(new Color(110, 3, 3));

        for (int i = 1; i < 4; i++) {
            try {
                BufferedImage original = ImageIO.read(new File("data/Carte_" + i + ".png"));
                Image image = original.getScaledInstance(114/*(1024/9)*/, 82/*(734/9)*/, Image.SCALE_DEFAULT);

                BufferedImage recadrage = Utilitaires.toBufferedImage(image).getSubimage(0, 0, 31, 82);

                ImageIcon icone = new ImageIcon(recadrage);
                JLabel label = new JLabel(icone);
                panel.add(label);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return panel;
    }

    private JPanel creerInformations()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBounds(this.getX(), this.getY()+this.uvs.getHeight()+this.competences.getHeight(), this.LARGEUR,this.HAUTEUR-this.uvs.getHeight()-this.competences.getHeight());
        panel.setBackground(new Color(6, 134, 0));
        JLabel numeroJoueur = new JLabel("Joueur 1");
        JLabel nomJoueur = new JLabel("Ugo");
        panel.add(numeroJoueur);
        panel.add(nomJoueur);

        return panel;
    }

}
