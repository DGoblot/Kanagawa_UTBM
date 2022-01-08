package net.piagoblotguinot.vue;

import net.piagoblotguinot.controleur.Controleur;

import javax.swing.*;
import java.awt.*;
/*
        Classe qui gère la création d'une nouvelle partie
*/
public class PanneauNouvellePartie extends JPanel
{
    private static final Point POS_LABEL_TITRE = new Point(30,10);
    private static final Point POS_JOUEUR_1 = new Point(450,150);
    private static final Point POS_JOUEUR_2 = new Point(450,200);
    private static final Point POS_JOUEUR_3 = new Point(450,250);
    private static final Point POS_JOUEUR_4 = new Point(450,300);
    private static final Point POS_BOUTON_RETOUR = new Point(550,400);
    private static final Point POS_BOUTON_VALIDER = new Point(650,400);
    private static final Point POS_TEXT_FIELD_1 = new Point(550,150);
    private static final Point POS_TEXT_FIELD_2 = new Point(550,200);
    private static final Point POS_TEXT_FIELD_3 = new Point(550,250);
    private static final Point POS_TEXT_FIELD_4 = new Point(550,300);

    Controleur controleur;
    Dimension dimensionBouton;

    JLabel labelTitre;
    JLabel[] labelJoueur;
    JButton boutonRetour, boutonValider, boutonAjoutJoueur, boutonSupprJoueur;
    JTextField[] textFieldJoueur;

    int nbJoueurs;

    PanneauNouvellePartie(Controleur controleur)
    {
        this.controleur = controleur;
        this.dimensionBouton = new Dimension(80,30);

        this.labelTitre = creerLabelTitre();

        this.labelJoueur = new JLabel[4];
        labelJoueur[0] = creerLabelJoueur(0);
        labelJoueur[1] = creerLabelJoueur(1);
        labelJoueur[2] = creerLabelJoueur(2);
        labelJoueur[3] = creerLabelJoueur(3);

        this.boutonRetour = creerBoutonRetour();
        this.boutonValider = creerBoutonValider();
        this.boutonAjoutJoueur = creerBoutonAjoutJoueur(new Point(377,247)); /* Position du premier bouton d'ajout */
        this.boutonSupprJoueur = creerBoutonSupprJoueur(new Point(0,0));

        this.textFieldJoueur = new JTextField[4];
        textFieldJoueur[0] = creerTextFieldJoueur(0);
        textFieldJoueur[1] = creerTextFieldJoueur(1);
        textFieldJoueur[2] = creerTextFieldJoueur(2);
        textFieldJoueur[3] = creerTextFieldJoueur(3);

        this.nbJoueurs = 2;

        initialiser();
    }

    private void initialiser()
    {
        this.setBackground(new Color(45, 45, 45));
        this.setLayout(null);

        this.add(labelTitre);
        this.add(labelJoueur[0]);
        this.add(labelJoueur[1]);
        this.add(boutonRetour);
        this.add(boutonValider);

        this.add(boutonAjoutJoueur);

        this.add(textFieldJoueur[0]);
        this.add(textFieldJoueur[1]);

    }

    public JLabel creerLabelTitre() /* POSITION FIXE */
    {
        JLabel label =  new JLabel("Nouvelle partie");
        label.setFont(new Font("Source Code Pro", Font.BOLD, 24));
        label.setSize(new Dimension(300,30));
        label.setLocation(POS_LABEL_TITRE.x, POS_LABEL_TITRE.y);
        return label;
    }

    public JButton creerBoutonRetour() /* POSITION FIXE */
    {
        JButton button = new JButton("Retour");
        button.setForeground(new Color(2, 2, 12));
        button.setSize(this.dimensionBouton);
        button.setLocation(POS_BOUTON_RETOUR.x, POS_BOUTON_RETOUR.y);
        button.setBackground(Color.white);
        button.addActionListener(e -> this.controleur.getEvenements().retourMenuPrincipal());
        return button;
    }

    public JButton creerBoutonValider() /* POSITION FIXE */
    {
        JButton button = new JButton("GO");
        button.setForeground(new Color(2, 2, 12));
        button.setSize(this.dimensionBouton);
        button.setLocation(POS_BOUTON_VALIDER.x, POS_BOUTON_VALIDER.y);
        button.setBackground(Color.white);
        button.addActionListener(e -> this.controleur.getEvenements().valider());
        return button;
    }

    public JLabel creerLabelJoueur(int i)
    {
        JLabel label = new JLabel("Joueur "+ (i+1));
        label.setForeground(Color.white);
        label.setFont(new Font("Source Code Pro", Font.BOLD, 16));
        label.setSize(new Dimension(100,30));
        switch (i) {
            case 0 : label.setLocation(POS_JOUEUR_1.x, POS_JOUEUR_1.y); break;
            case 1 : label.setLocation(POS_JOUEUR_2.x, POS_JOUEUR_2.y); break;
            case 2 : label.setLocation(POS_JOUEUR_3.x, POS_JOUEUR_3.y); break;
            case 3 : label.setLocation(POS_JOUEUR_4.x, POS_JOUEUR_4.y); break;
        }
        return label;
    }
    /*
        Permet à l'utilisateur de rentrer le nombre de joueurs et leur nom
    */
    public JTextField creerTextFieldJoueur(int i)
    {
        JTextField textField = new JTextField(15);
        textField.setFont(new Font("Source Code Pro", Font.BOLD, 16));
        textField.setSize(new Dimension(100,30));
        switch (i) {
            case 0 : textField.setLocation(POS_TEXT_FIELD_1.x, POS_TEXT_FIELD_1.y); break;
            case 1 : textField.setLocation(POS_TEXT_FIELD_2.x, POS_TEXT_FIELD_2.y); break;
            case 2 : textField.setLocation(POS_TEXT_FIELD_3.x, POS_TEXT_FIELD_3.y); break;
            case 3 : textField.setLocation(POS_TEXT_FIELD_4.x, POS_TEXT_FIELD_4.y); break;
        }
        return textField;
    }

    public JButton creerBoutonAjoutJoueur(Point p) /* POSITION NON FIXE */
    {
        JButton button = new JButton("+");
        button.setBorderPainted(false);
        button.setFont(new Font("Source Code Pro", Font.BOLD, 24));
        button.setForeground(Color.white);
        button.setSize(60,40);
        button.setLocation(p);
        button.setBackground(new Color(2,2,12));
        button.addActionListener(e -> this.controleur.getEvenements().ajouterJoueur());
        return button;
    }

    public JButton creerBoutonSupprJoueur(Point p) /* POSITION NON FIXE */
    {
        JButton button = new JButton("-");
        button.setBorderPainted(false);
        button.setFont(new Font("Source Code Pro", Font.BOLD, 24));
        button.setForeground(Color.white);
        button.setSize(60,40);
        button.setLocation(p);
        button.setBackground(new Color(2,2,12));
        button.addActionListener(e -> this.controleur.getEvenements().supprimerJoueur());
        return button;
    }

    public void ajouterJoueur()
    {
        if(this.nbJoueurs == 2)
        {
            this.nbJoueurs = 3;
            this.boutonAjoutJoueur.setLocation(380,300); /* Nouvelle position pour les boutons */
            this.boutonSupprJoueur.setLocation(380,245);
            this.add(this.labelJoueur[2]);
            this.add(this.textFieldJoueur[2]);
            this.add(this.boutonSupprJoueur);
        }
        else if(this.nbJoueurs == 3)
        {
            this.nbJoueurs = 4;
            this.boutonSupprJoueur.setLocation(380,300);
            this.add(this.labelJoueur[3]);
            this.add(this.textFieldJoueur[3]);
            this.remove(this.boutonAjoutJoueur);

        }
        this.revalidate();
        this.getParent().repaint();
    }

    public void supprimerJoueur()
    {
        if(nbJoueurs == 3)
        {
            this.nbJoueurs = 2;
            this.remove(this.labelJoueur[2]);
            this.remove(this.textFieldJoueur[2]);
            this.remove(this.boutonSupprJoueur);
            this.boutonAjoutJoueur.setLocation(377,247);
        }
        else if(nbJoueurs == 4)
        {
            this.nbJoueurs = 3;
            this.remove(labelJoueur[3]);
            this.remove(textFieldJoueur[3]);
            this.boutonAjoutJoueur.setLocation(380,300);
            this.boutonSupprJoueur.setLocation(380,245);
            this.add(boutonAjoutJoueur);

        }
        this.revalidate();
        this.getParent().repaint();
    }
    /*
        Refuse de lancer la partie si des joueurs n'ont pas de nom
    */
    public void joueurSansNom()
    {
        JOptionPane.showMessageDialog(new JFrame(),"Certains joueurs n'ont pas de nom...");
    }

    /* GETTERS */
    public int getNbJoueurs(){return nbJoueurs;}

    public String getTextFieldJoueur(int i)
    {
        return this.textFieldJoueur[i].getText();
    }
}
