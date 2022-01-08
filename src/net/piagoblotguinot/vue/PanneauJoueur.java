package net.piagoblotguinot.vue;

import net.piagoblotguinot.controleur.Controleur;
import net.piagoblotguinot.controleur.Main;
import net.piagoblotguinot.modèle.Competence;
import net.piagoblotguinot.modèle.Uv;
import net.piagoblotguinot.utilitaires.Utilitaires;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
/*
        Panneau d'affichage des UV et compétences de chaques joueurs
*/
public class PanneauJoueur extends JPanel
{
    private Controleur controleur;
    private Point emplacement;
    private JPanel uvs, competences, informations;
    private final int LARGEUR = 570, HAUTEUR = 515; // Dimensions du panneau joueur

    public PanneauJoueur(Controleur controleur, Point emplacement, int numero)
    {
        this.controleur = controleur;
        this.emplacement = emplacement;
        this.uvs = creerUvs();
        this.competences = creerCompetences();
        this.informations = creerInformations(numero-1);

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

    /*
        Affichage des UV
    */
    private JPanel creerUvs() // DIMENSIONS : this.LARGEUR, 332
    {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBounds(this.getX(),this.getY(),this.LARGEUR,332);
        panel.setBackground(new Color(45, 45, 45));
        /*for(int i = 1; i <16;i++)
        {
            try
            {
                BufferedImage original = ImageIO.read(new File("data/cartes/Carte_"+ i +".png"));
                Image image = original.getScaledInstance((1024/7),(734/7),Image.SCALE_SMOOTH);
                                                            // 146*105
                BufferedImage recadrage = Utilitaires.toBufferedImage(image).getSubimage(39, 0, 106,104);

                ImageIcon icone = new ImageIcon(recadrage);
                JLabel label = new JLabel(icone);
                panel.add(label);
            } catch(Exception e){e.printStackTrace();}
        }*/
        return panel;
    }
    /*
        Affichage des compétences
    */
    private JPanel creerCompetences()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBounds(this.getX(), this.getY()+this.uvs.getHeight(), this.LARGEUR, 115);
        panel.setBackground(new Color(81, 81, 81));

        /*
        for (int i = 1; i < 12; i++) {
            try {
                BufferedImage original = ImageIO.read(new File("data/cartes/Carte_" + i + ".png"));
                Image image = original.getScaledInstance((1024/7),(734/7),Image.SCALE_SMOOTH);
                                                            // 146*105

                BufferedImage recadrage = Utilitaires.toBufferedImage(image).getSubimage(0, 0, 39, 104);

                ImageIcon icone = new ImageIcon(recadrage);
                JLabel label = new JLabel(icone);
                panel.add(label);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
         */

        return panel;
    }
    /*
        Affichage des bonus du joueur
    */
    private JPanel creerInformations(int n)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBounds(this.getX(), this.getY()+this.uvs.getHeight()+this.competences.getHeight(), this.LARGEUR,this.HAUTEUR-this.uvs.getHeight()-this.competences.getHeight());
        panel.setBackground(new Color(23, 23, 23));
        JLabel numeroJoueur = new JLabel("Joueur "+(n+1));
        numeroJoueur.setForeground(Color.white);
        JLabel nomJoueur = new JLabel(Main.controleur.nomsJoueurs()[n]);
        nomJoueur.setForeground(Color.white);
        panel.add(numeroJoueur);
        panel.add(nomJoueur);

        return panel;
    }
    /*
        ajout d'une compétence dans le panneau du joueur en cours
    */
    public void ajouterCompetence(Competence competence){
        try {
            BufferedImage original = ImageIO.read(new File("data/cartes/Carte_" + competence.getIdentifiant() + ".png"));
            Image image = original.getScaledInstance((1024/7),(734/7),Image.SCALE_SMOOTH);
            // 146*105

            BufferedImage recadrage = Utilitaires.toBufferedImage(image).getSubimage(0, 0, 39, 104);

            ImageIcon icone = new ImageIcon(recadrage);
            JLabel label = new JLabel(icone);
            this.competences.add(label);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    /*
        ajout d'un UV dans le panneau du joueur en cour
    */
    public void ajouterUv(Uv uv){
        try {
            BufferedImage original = ImageIO.read(new File("data/cartes/Carte_" + uv.getIdentifiant() + ".png"));
            Image image = original.getScaledInstance((1024/7),(734/7),Image.SCALE_SMOOTH);
            // 146*105

            BufferedImage recadrage = Utilitaires.toBufferedImage(image).getSubimage(39, 0, 106,104);

            ImageIcon icone = new ImageIcon(recadrage);
            JLabel label = new JLabel(icone);
            this.uvs.add(label);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
