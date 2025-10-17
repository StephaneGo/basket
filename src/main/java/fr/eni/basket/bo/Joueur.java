package fr.eni.basket.bo;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

public class Joueur {
    private int noJoueur;

    private String nom;

    private String prenom;

    private String email;

    private Equipe equipe ;

    public Joueur() {
    }


    public Joueur(int noJoueur, String nom, String prenom, Equipe equipe) {
        this.noJoueur = noJoueur;
        this.nom = nom;
        this.prenom = prenom;
        this.equipe = equipe;
    }

    public Joueur(int noJoueur, String nom, String prenom, String email,  Equipe equipe) {
        this.noJoueur = noJoueur;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.equipe = equipe;
    }


    public Joueur(String nom, String prenom, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    public int getNoJoueur() {
        return noJoueur;
    }

    public void setNoJoueur(int noJoueur) {
        this.noJoueur = noJoueur;
    }

    public String getNom() {
        return nom;
    }



    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Equipe getEquipe() {
        return equipe;
    }

    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Joueur joueur)) return false;
        return Objects.equals(nom, joueur.nom) && Objects.equals(prenom, joueur.prenom) && Objects.equals(email, joueur.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nom, prenom, email);
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "noJoueur=" + noJoueur +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", equipe=" + equipe +
                '}';
    }
}
