package fr.eni.basket.controllers;

import fr.eni.basket.bll.EquipeService;
import fr.eni.basket.bo.Equipe;
import fr.eni.basket.dal.EquipeDAO;
import fr.eni.basket.dto.EquipeDTO;
import fr.eni.basket.exceptions.EquipeDejaExistante;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class EquipeRestController {
    private EquipeService equipeService;

    @Autowired
    private EquipeDAO equipeDAO;

    public EquipeRestController( EquipeService equipeService){
        this.equipeService = equipeService;
    }

    @GetMapping("/test")
    public List<Equipe> findAll(){
        return equipeDAO.findAllEquipes();
    }

    @GetMapping("/equipes")
    public List<Equipe> findAllEquipes() {
        return equipeService.getEquipes();
    }


    @GetMapping("/equipes/{nom}")
    public ResponseEntity<Equipe> findEquipeByNom(@PathVariable("nom") String nom){
        Optional<Equipe> equipeOpt = equipeService.findEquipeByNom(nom);

        if(equipeOpt.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(equipeOpt.get());
    }

/*
    @PostMapping("/equipes")
    public Equipe addEquipe(@RequestBody EquipeDTO equipeDto) {
        System.out.println("add equipe " + equipeDto    );

        Equipe newEquipe = equipeService.addEquipe(equipeDto);

        return newEquipe;
    }
*/

    @PostMapping("/equipes")
    public ResponseEntity<Equipe> addEquipe( @Valid @RequestBody EquipeDTO equipeDto, BindingResult result ) {
        System.out.println("add equipe " + equipeDto    );

        if(result.hasErrors()){
            return ResponseEntity.badRequest().build();
        }

        Equipe newEquipe = null;
        try {
            newEquipe = equipeService.addEquipe(equipeDto);
        }catch(EquipeDejaExistante ede){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(newEquipe);
    }

    @DeleteMapping(value = "/equipes/{noEquipe}")
    public ResponseEntity<?> deleteEquipe(@PathVariable("noEquipe") Integer noEquipe) {

        equipeService.deleteEquipe(noEquipe);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
