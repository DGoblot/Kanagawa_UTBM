package net.piagoblotguinot.modèle;

public class Carte
{
    protected Uv uv;
    protected Competence competence;

    public Carte()
    {
        this.uv = new Uv();
        this.competence = new Competence();
    }

    protected void aff()
    {
        System.out.print("Domaine : ");
        System.out.println(competence.domaine);

        System.out.print("Objet : ");
        System.out.println(competence.objet);

        System.out.print("ECTS : ");
        System.out.println(competence.ects);

        System.out.print("Domaine : ");
        System.out.println(uv.domaine);

        System.out.print("Nombre de domaines : ");
        System.out.println(uv.nombre);

        System.out.print("Année : ");
        System.out.println(uv.annee);

        System.out.print("ECTS : ");
        System.out.println(uv.ects);

        System.out.print("Approche : ");
        System.out.println(uv.approche);

        System.out.print("Type : ");
        System.out.println(uv.type);

    }
}
