package fr.eni.basket.dal;

import fr.eni.basket.bo.Equipe;
import fr.eni.basket.dto.EquipeDTO;

import java.util.List;
import java.util.Optional;

public interface EquipeDAO {
    List<Equipe> findAllEquipes();

    Optional<Equipe> findEquipeByNom(String nom);

    void deleteEquipe(int noEquipe);


    void deleteEquipeByNom(String nomEquipe);

    public Equipe insertEquipe(EquipeDTO equipeDto);

}
