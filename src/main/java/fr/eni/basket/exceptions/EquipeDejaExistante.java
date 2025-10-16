package fr.eni.basket.exceptions;

public class EquipeDejaExistante extends RuntimeException {

    public EquipeDejaExistante(String nomEquipe) {
        super("L'equipe " + nomEquipe + " existe déjà.");
    }
}
