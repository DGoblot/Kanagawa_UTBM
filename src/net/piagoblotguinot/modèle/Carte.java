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
        System.out.println("Domaine : " + competence.domaine);

        System.out.println("Objet : " + competence.objet);

        System.out.println("ECTS : " + competence.ects);

        System.out.println("Domaine : " + uv.domaine);

        System.out.println("Nombre de domaines : " + uv.nombre);

        System.out.println("Année : " + uv.annee);

        System.out.println("ECTS : " + uv.ects);

        System.out.println("Approche : " + uv.approche);

        System.out.println("Type : " + uv.type);

    }
}
