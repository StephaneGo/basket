package fr.eni.basket.dal;


import fr.eni.basket.bo.Joueur;
import fr.eni.basket.dto.JoueurDTO;

import java.util.List;
import java.util.Optional;

public interface JoueurDAO {
    List<Joueur> findAllJoueurs();

    List<Joueur> findJoueursByNomEquipe(String nomEquipe);

    Optional<Joueur> findJoueurByNoJoueur(int noJoueur);

    Joueur saveJoueur(JoueurDTO joueur);
}
