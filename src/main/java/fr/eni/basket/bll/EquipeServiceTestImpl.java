package fr.eni.basket.bll;

import fr.eni.basket.bo.Equipe;
import fr.eni.basket.dto.EquipeDTO;
import fr.eni.basket.exceptions.EchecAjoutEquipe;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EquipeServiceTestImpl implements EquipeService {

    private Set<Equipe> equipes;
    private static int noEquipeIndex = 1;

    private EquipeServiceTestImpl() {
        this.equipes = new HashSet<Equipe>();
        this.equipes.add(new Equipe(noEquipeIndex++, "U15F1"));
        this.equipes.add(new Equipe(noEquipeIndex++, "U15F2"));
        this.equipes.add(new Equipe(noEquipeIndex++, "U18M1"));
    }

    public void setEquipes(List<Equipe> equipes) {
        this.equipes = new HashSet<>(equipes);
    }

    @Override
    public List<Equipe> getEquipes() {
        return equipes.stream().toList();
    }

    @Override
    public Equipe addEquipe(EquipeDTO equipeDto) {

        Equipe newEquipe = new Equipe();
        newEquipe.setNoEquipe(noEquipeIndex++);
        BeanUtils.copyProperties(equipeDto, newEquipe);
        boolean ajout = equipes.add(newEquipe);
        if(!ajout) {
            throw new EchecAjoutEquipe("Echec d'ajout de l'equipe " + equipeDto.nom());
        }

        return newEquipe;
    }

    @Override
    public void deleteEquipe(int noEquipe) {
        for(Equipe equipe : equipes){
            if(equipe.getNoEquipe() == noEquipe) {
                equipes.remove(equipe);
                break;
            }
        }
    }

    @Override
    public Optional<Equipe> findEquipeByNom(String nom) {
        return equipes.stream().filter(equipe -> equipe.getNom().equals(nom)).findFirst();
    }

}
