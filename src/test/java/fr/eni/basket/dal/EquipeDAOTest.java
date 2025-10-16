package fr.eni.basket.dal;

import fr.eni.basket.bo.Equipe;
import fr.eni.basket.dto.EquipeDTO;
import fr.eni.basket.exceptions.EquipeDejaExistante;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EquipeDAOTest {
    @Autowired
    private EquipeDAO equipeDAO;

    @Test
    @DisplayName("Test DAO de findEquipeByNom Cas le nom de l'equipe existe")
    public void findEquipeByNom(){

        //Act
        Optional<Equipe> equipeOpt = equipeDAO.findEquipeByNom("U15F1");

        //Assert
        assertTrue(equipeOpt.isPresent());
        assertEquals(equipeOpt.get().getNom(), "U15F1");

    }

    @Test
    @DisplayName("Test DAO de findEquipeByNom Cas l'equipe n'existe pas")
    public void findEquipeByNomCasNonTrouve(){

        //Act
        Optional<Equipe> equipeOpt = equipeDAO.findEquipeByNom("XXXXXXXX");

        //Assert
        assertTrue(equipeOpt.isEmpty());

    }

    @Test
    public  void testFindAllEquipes()  {
        //Arrange + Act
        List<Equipe> equipes = equipeDAO.findAllEquipes();

        System.out.println(equipes);

        assertEquals(equipes.size(), 2);
    }

    @Test
    @DisplayName("Test DAO de insertEquipe cas données valides")
    @Transactional //Permet d'annuler l'insert à la fin du test
    public void insertEquipeCasOk(){

        //Arrange
        String nomEquipe = "U18M3";
        equipeDAO.deleteEquipeByNom(nomEquipe);
        EquipeDTO equipeDto = new EquipeDTO(nomEquipe);

        //Act
        Equipe newEquipe = equipeDAO.insertEquipe(equipeDto);

        //Assert
        assertNotNull(newEquipe);
        assertTrue(newEquipe.getNoEquipe()>0);
        Optional<Equipe> equipeOpt = equipeDAO.findEquipeByNom(nomEquipe);
        assertTrue(equipeOpt.isPresent());
        assertEquals(nomEquipe, equipeOpt.get().getNom());

    }

    @Test
    @DisplayName("Test DAO de insertEquipe cas equipe deja existante")
    public void insertEquipeCasEquipeDejaExistante(){

        //Arrange
        String nomEquipe = "EQUIPE_A_SUPPRIMER";
        EquipeDTO equipeDto = new EquipeDTO(nomEquipe);
        equipeDAO.deleteEquipeByNom(nomEquipe);
        equipeDAO.insertEquipe(equipeDto);

        //Act et Assert
        assertThrows(EquipeDejaExistante.class, ()->equipeDAO.insertEquipe(equipeDto));


    }

}
