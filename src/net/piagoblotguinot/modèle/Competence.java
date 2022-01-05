package net.piagoblotguinot.modèle;

public class Competence
{
    String domaine;
    String objet;
    int ects;
    Ordinateur ordi;

    public Competence()
    {
        this.domaine = "None";
        this.objet = "None";
        this.ects = 0;
        this.ordi = new Ordinateur();
    }

    public void aff(boolean posee) {

        System.out.println("Domaine : " + domaine);
        System.out.println("Objet : " + objet);
        System.out.println("ECTS : " + ects);
        if (posee)
        {
            System.out.print("Ordi présent : ");
            if(!ordi.exists){
                System.out.println("non");
            } else {
                System.out.println("oui");
                System.out.println("Ordi actif : "+ordi.actif);
            }
        }
        System.out.println();
    }
}
