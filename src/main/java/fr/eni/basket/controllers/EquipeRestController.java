package fr.eni.basket.controllers;

import fr.eni.basket.bll.EquipeService;
import fr.eni.basket.bo.Equipe;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EquipeRestController {
    private EquipeService equipeService;

    public EquipeRestController( EquipeService equipeService){
        this.equipeService = equipeService;
    }

    @GetMapping("/equipes")
    public List<Equipe> findAllEquipes() {
        return equipeService.getEquipes();
    }

}
