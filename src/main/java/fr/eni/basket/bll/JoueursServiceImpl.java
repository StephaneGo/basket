package fr.eni.basket.bll;


import fr.eni.basket.bo.Equipe;
import fr.eni.basket.bo.Joueur;
import fr.eni.basket.dal.JoueurDAO;
import fr.eni.basket.dto.JoueurDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JoueursServiceImpl implements JoueursService {

    private JoueurDAO joueurDAO;

    public JoueursServiceImpl(JoueurDAO joueurDAO) {
        this.joueurDAO = joueurDAO;
    }

    //Retourne la liste de tous les joueurs
    public List<Joueur> getJoueurs() {
        //renvoit une copie de la liste
        return joueurDAO.findAllJoueurs();
    }

    @Override
    public List<Joueur> getJoueursByNomEquipe(String nomEquipe) {
        return joueurDAO.findJoueursByNomEquipe(nomEquipe);
    }

    //Retourne un joueur connaissant son id
    public Optional<Joueur> getJoueur(int noJoueur){
        return joueurDAO.findJoueurByNoJoueur(noJoueur);
    }

    @Override
    public Joueur ajouterJoueur(JoueurDTO joueur) {
        return joueurDAO.saveJoueur(joueur);
    }

}
