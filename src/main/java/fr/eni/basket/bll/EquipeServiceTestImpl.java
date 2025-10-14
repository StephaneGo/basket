package fr.eni.basket.bll;

import fr.eni.basket.bo.Equipe;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class EquipeServiceTestImpl implements EquipeService {

    private Set<Equipe> equipes;

    private EquipeServiceTestImpl() {
        this.equipes = new HashSet<Equipe>();
        this.equipes.add(new Equipe(1, "U15F1"));
        this.equipes.add(new Equipe(2, "U15F2"));
        this.equipes.add(new Equipe(3, "U18M1"));
    }

    public void setEquipes(List<Equipe> equipes) {
        this.equipes = new HashSet<>(equipes);
    }

    @Override
    public List<Equipe> getEquipes() {
        return equipes.stream().toList();
    }
}
