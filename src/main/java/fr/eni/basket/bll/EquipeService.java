package fr.eni.basket.bll;

import fr.eni.basket.bo.Equipe;
import fr.eni.basket.dto.EquipeDTO;

import java.util.List;
import java.util.Optional;

public interface EquipeService {

    /* Retourne la liste de toutes les équipes */
    //public abstract List<Equipe> getEquipes();
    List<Equipe> getEquipes();

    /* Ajouter une équipe à la liste des équipes */
    Equipe addEquipe(EquipeDTO equipeDto);

    void deleteEquipe(int noEquipe);

    Optional<Equipe> findEquipeByNom(String nom);

}
