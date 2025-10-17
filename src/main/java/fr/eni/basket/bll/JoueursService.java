package fr.eni.basket.bll;


import fr.eni.basket.bo.Joueur;
import fr.eni.basket.dto.JoueurDTO;

import java.util.List;
import java.util.Optional;

public interface JoueursService {
    List<Joueur> getJoueurs();

    List<Joueur> getJoueursByNomEquipe(String nomEquipe);

    Optional<Joueur> getJoueur(int id);

    Joueur ajouterJoueur(JoueurDTO joueur);
}
