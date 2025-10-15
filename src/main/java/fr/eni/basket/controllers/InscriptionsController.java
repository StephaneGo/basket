package fr.eni.basket.controllers;

import fr.eni.basket.bll.JoueursService;
import fr.eni.basket.bo.Joueur;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class InscriptionsController {
    private JoueursService joueursService;

    public InscriptionsController(JoueursService joueursService){
        this.joueursService = joueursService;
    }

    @GetMapping("/joueurs")
    public List<Joueur> getJoueurs(){
        return joueursService.getJoueurs();
    }

    /* Lister les joueurs d'une équipe connaissant le nom de l'équipe */
    @GetMapping(value = "/joueurs", params = "nomEquipe")
    public List<Joueur> getJoueursByNomEquipe(@RequestParam(name="nomEquipe") String nomEquipe){
       List<Joueur> joueurs = joueursService.getJoueursByNomEquipe(nomEquipe);
        return joueurs;
    }


    @GetMapping("/joueurs/{idJoueur}")
    public ResponseEntity<Joueur> getJoueur(@PathVariable(name="idJoueur") int noJoueur){
        Optional<Joueur> joueur = joueursService.getJoueur(noJoueur);
        if(joueur.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.ok(joueur.get());
    }

    /*
    @PostMapping("/joueurs")
    public ResponseEntity<ApiResponse<Joueur>>  ajouterJoueur(@Valid @RequestBody Joueur joueur, BindingResult result){
        Joueur newJoueur = inscriptionsService.ajouterJoueur(joueur);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse<>(true, "", newJoueur));
    }*/

}
