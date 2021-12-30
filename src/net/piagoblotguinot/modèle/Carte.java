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
        System.out.println("Compétence :");
        this.competence.aff(false);
        System.out.println("UV :");
        this.uv.aff();


    }
}
