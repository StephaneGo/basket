package fr.eni.basket.bll;

import fr.eni.basket.bo.Equipe;
import fr.eni.basket.dal.EquipeDAO;
import fr.eni.basket.dto.EquipeDTO;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Primary
public class EquipeServiceImpl implements EquipeService {

    private EquipeDAO equipeDAO;

    public EquipeServiceImpl(EquipeDAO equipeDAO){
        this.equipeDAO = equipeDAO;
    }

    @Override
    public List<Equipe> getEquipes() {
        return equipeDAO.findAllEquipes();
    }

    @Override
    public Equipe addEquipe(EquipeDTO equipeDto) {
        return equipeDAO.insertEquipe(equipeDto);
    }

    @Override
    public void deleteEquipe(int noEquipe) {
        equipeDAO.deleteEquipe(noEquipe);
    }

    @Override
    public Optional<Equipe> findEquipeByNom(String nom) {

        return equipeDAO.findEquipeByNom(nom);
    }
}
