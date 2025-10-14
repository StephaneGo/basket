package fr.eni.basket.bo;

import java.util.Objects;

public class Equipe {
    private int noEquipe;
    private String nom;

    public Equipe(int noEquipe, String nom) {
        this.noEquipe = noEquipe;
        this.nom = nom;
    }

    public int getNoEquipe() {
        return noEquipe;
    }

    public void setNoEquipe(int noEquipe) {
        this.noEquipe = noEquipe;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Equipe equipe)) return false;
        return Objects.equals(nom, equipe.nom);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nom);
    }

    @Override
    public String toString() {
        return "Equipe{" +
                "noEquipe=" + noEquipe +
                ", nom='" + nom + '\'' +
                '}';
    }
}
