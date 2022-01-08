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

    public Uv getUv() {
        return uv;
    }

    public Competence getCompetence() {
        return competence;
    }
}
