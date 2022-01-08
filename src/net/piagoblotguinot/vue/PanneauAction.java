package net.piagoblotguinot.vue;

import net.piagoblotguinot.controleur.Controleur;
import net.piagoblotguinot.modèle.Carte;
import net.piagoblotguinot.modèle.Filiere;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PanneauAction extends JPanel
{
    private static final Point POS_BOUTON_POSER_ORDI = new Point(30+180+180,150);
    private static final Point POS_BOUTON_BOUGER_ORDI = new Point(30+180+180+180,150);
    private static final Point POS_BOUTON_POSER_UV = new Point(30,150);
    private static final Point POS_BOUTON_POSER_COMPETENCE = new Point(30+180,150);
    private static final Point POS_BOUTON_FINIR_TOUR = new Point(30+180,325);
    private static final Point POS_PANNEAU_CARTES_PRISES = new Point(30,200);
    private static final Point POS_BOUTON_RETOUR_MENU = new Point(30+180+180,325);
    public static final int LARGEUR = 752, HAUTEUR = 380; // Middle : 376, 190

    private Controleur controleur;
    private Point emplacement;

    private Bouton boutonPoserOrdi;
    private Bouton boutonBougerOrdi;
    private Bouton boutonPoserUV;
    private Bouton boutonPoserCompetence;
    private Bouton boutonFinirTour;
    private Bouton boutonRetourMenu;
    private JPanel panneauCartesPrises;
    private JLabel[] cartesPrises = new JLabel[7];
    private JLabel labelInfo;

    public PanneauAction(Controleur controleur,Point emplacement)
    {
        this.controleur = controleur;
        this.emplacement = emplacement;
        this.boutonPoserOrdi = creerBoutonPoserOrdi();
        this.boutonBougerOrdi = creerBoutonBougerOrdi();
        this.boutonPoserUV = creerBoutonPoserUV();
        this.boutonPoserCompetence = creerBoutonPoserCompetence();
        this.boutonFinirTour = creerBoutonFinirTour();
        this.panneauCartesPrises = creerPanneauCartesPrises();
        this.labelInfo = creerLabelInfo();
        this.boutonRetourMenu = creerBoutonRetourMenu();

        initialiser();
    }

    public void initialiser()
    {
        this.setLayout(null);
        this.setBounds(emplacement.x,emplacement.y,this.LARGEUR,this.HAUTEUR);
        this.setBackground(new Color(23, 23, 23));
        this.add(this.boutonPoserCompetence);
        this.add(this.boutonPoserUV);
        this.add(this.boutonPoserOrdi);
        this.add(this.boutonBougerOrdi);
        this.add(this.boutonFinirTour);
        this.add(this.panneauCartesPrises);
        this.add(labelInfo);
        this.add(boutonRetourMenu);
        initMain();
    }

    private Bouton creerBoutonRetourMenu()
    {
        Bouton bouton = new Bouton("Retour");
        bouton.setLocation(POS_BOUTON_RETOUR_MENU);
        bouton.addActionListener(e -> this.controleur.getEvenements().retourMenuPrincipal());
        return bouton;
    }

    public int prendreFiliere(Filiere filiere)
    {
        int choix = 0;
        try {
            BufferedImage original = ImageIO.read(new File("data/diplomes/"+filiere.getIdentifiant()+".png"));
            Image image = original.getScaledInstance((512/6), (600/6), Image.SCALE_SMOOTH);
            // 146*105
            ImageIcon icone = new ImageIcon(image);
            choix = JOptionPane.showConfirmDialog(this,"Prendre le diplôme ?","Veuillez faire un choix",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,icone);
        } catch (Exception e) {}
        System.out.println("Choix : " + choix);

        return 1-choix;
    }

    public JLabel creerLabelInfo()
    {
        JLabel label = new JLabel();
        label.setText("Jossua doit placer ses cartes.");
        label.setFont(new Font("Monospaced", Font.BOLD, 15));
        label.setSize(new Dimension(500,40));
        label.setLocation(250,10);
        label.setForeground(Color.white);
        return label;
    }

    public void changerNomJoueurActif(String nom){this.labelInfo.setText(nom+" doit placer ses cartes.");}

    public JPanel creerPanneauCartesPrises()
    {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBounds(POS_PANNEAU_CARTES_PRISES.x,POS_PANNEAU_CARTES_PRISES.y,700,120);
        panel.setBackground(new Color(23, 23, 23));
        return panel;
    }

    private void viderMain(){
        BufferedImage original = null;
        try {
            original = ImageIO.read(new File("data/cartes/Carte_vide.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        ImageIcon carteVide = new ImageIcon(original);

        for (int i = 1; i < 5; i++) {
            cartesPrises[i-1].setIcon(carteVide);

        }
    }

    private void initMain(){
        for(int i = 1; i < 5;i++)
        {
            try
            {
                BufferedImage original = ImageIO.read(new File("data/cartes/Carte_"+ i +".png"));
                Image image = original.getScaledInstance((1024/7),(734/7),Image.SCALE_SMOOTH);
                // 146*105

                ImageIcon icone = new ImageIcon(image);
                JLabel label = new JLabel(icone);
                cartesPrises[i-1] = label;
                this.panneauCartesPrises.add(label);
            } catch(Exception e){e.printStackTrace();}
        }
        viderMain();

    }

    public void ajouterCartesPrises(ArrayList<Carte> main)
    {
        viderMain();
        for(int i = 0; i < main.size();i++)
        {
            try
            {
                BufferedImage original = ImageIO.read(new File("data/cartes/Carte_"+ main.get(i).getUv().getIdentifiant() +".png"));
                Image image = original.getScaledInstance((1024/7),(734/7),Image.SCALE_SMOOTH);
                // 146*105

                ImageIcon icone = new ImageIcon(image);
                cartesPrises[i].setIcon(icone);
            } catch(Exception e){e.printStackTrace();}
        }
    }

    public int choisirColonne()
    {
        String[] domaine = { "1", "2", "3", "4"};
        String choix = (String) JOptionPane.showInputDialog(null, "Quelle colonne prendre ?", "Veuillez choisir", JOptionPane.QUESTION_MESSAGE, null,
                domaine,
                domaine[0]);

        return Integer.parseInt(choix);
    }
    public int choisirAction()
    {
        String[] domaine = { "Poser UV", "Poser compétence", "Poser ordi", "Bouger ordi","Fin du tour"};
        String choix = (String) JOptionPane.showInputDialog(null, "Quelle action ?", "Veuillez choisir", JOptionPane.QUESTION_MESSAGE, null,
                domaine,
                domaine[0]);

        switch (choix){
            case "Poser UV" -> {
                return 1;
            }
            case "Poser compétence" -> {
                return 2;
            }
            case "Poser ordi" -> {
                return 3;
            }
            case "Bouger ordi" -> {
                return 4;
            }
            case "Fin du tour" -> {
                return 5;
            }
            default -> {
                return -1;
            }

        }

    }



    public int choisirCarte()
    {
        String[] domaine = { "1", "2", "3", "4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20"};
        String choix = (String) JOptionPane.showInputDialog(null, "Quelle carte choisir ?", "Veuillez choisir", JOptionPane.QUESTION_MESSAGE, null,
                domaine,
                domaine[0]);

        return Integer.parseInt(choix);
    }

    private Bouton creerBoutonPoserOrdi()
    {
        Bouton bouton = new Bouton("Placer un ordi");
        bouton.setLocation(POS_BOUTON_POSER_ORDI);
        bouton.addActionListener(e -> this.controleur.getEvenements().poserOrdi());
        return bouton;
    }

    private Bouton creerBoutonBougerOrdi()
    {
        Bouton bouton = new Bouton("Déplacer un ordi");
        bouton.setLocation(POS_BOUTON_BOUGER_ORDI);
        bouton.addActionListener(e -> this.controleur.getEvenements().bougerOrdi());
        return bouton;
    }

    private Bouton creerBoutonPoserUV()
    {
        Bouton bouton = new Bouton("Poser une UV");
        bouton.setLocation(POS_BOUTON_POSER_UV);
        bouton.addActionListener(e -> this.controleur.getEvenements().poserUV());
        return bouton;
    }

    private Bouton creerBoutonPoserCompetence()
    {
        Bouton bouton = new Bouton("Poser une compétence");
        bouton.setLocation(POS_BOUTON_POSER_COMPETENCE);
        bouton.addActionListener(e -> this.controleur.getEvenements().poserCompetence());
        return bouton;
    }

    private Bouton creerBoutonFinirTour()
    {
        Bouton bouton = new Bouton("Finir le tour");
        bouton.setLocation(POS_BOUTON_FINIR_TOUR);
        bouton.addActionListener(e -> this.controleur.getEvenements().finDuTour());
        return bouton;
    }

    public String choisirPasser() {
        String[] domaine = { "Passer", "Prendre des cartes"};
        String choix = (String) JOptionPane.showInputDialog(null, "Quelle action ?", "Veuillez choisir", JOptionPane.QUESTION_MESSAGE, null,
                domaine,
                domaine[0]);

        return choix;
    }

}
