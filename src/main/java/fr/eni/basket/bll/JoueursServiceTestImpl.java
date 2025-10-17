package fr.eni.basket.bll;


import fr.eni.basket.bo.Equipe;
import fr.eni.basket.bo.Joueur;
import fr.eni.basket.dto.JoueurDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//@Service
public class JoueursServiceTestImpl implements JoueursService {
    //Pour la démo, on gère les données en mémoire
    private List<Joueur> joueurs;
    private int indexNoJoueur ;
    private Joueur joueur;
    private EquipeService equipeService;

    public JoueursServiceTestImpl(EquipeService equipeService) {
        this.equipeService = equipeService;
        joueurs = new ArrayList<>();
        indexNoJoueur = 1;
        Equipe equipeU15F1 = equipeService.findEquipeByNom("U15F1").orElse(null);
        Equipe equipeU15F2 = equipeService.findEquipeByNom("U15F2").orElse(null);

        joueurs.add(new Joueur(indexNoJoueur++, "nom1", "prenom1", "joueur1@eni.fr", equipeU15F1));
        joueurs.add(new Joueur(indexNoJoueur++, "nom2", "prenom2", "joueur2@eni.fr", equipeU15F2));
        joueurs.add(new Joueur(indexNoJoueur++, "nom3", "prenom3", equipeU15F1));
    }

    //Retourne la liste de tous les joueurs
    public List<Joueur> getJoueurs() {
        //renvoit une copie de la liste
        return joueurs.stream().toList();
    }

    @Override
    public List<Joueur> getJoueursByNomEquipe(String nomEquipe) {
        return joueurs.stream().filter(j -> j.getEquipe().getNom().equals(nomEquipe)).toList();
    }

    //Retourne un joueur connaissant son id
    public Optional<Joueur> getJoueur(int noJoueur){
        return joueurs.stream().filter(j -> j.getNoJoueur() == noJoueur).findFirst();
    }

    @Override
    public Joueur ajouterJoueur(JoueurDTO joueur) {
        Joueur newJoueur = new Joueur();
        newJoueur.setNoJoueur(indexNoJoueur++);
        joueurs.add(newJoueur);
        return newJoueur;
    }

}
