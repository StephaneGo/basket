package fr.eni.basket.bll;


import fr.eni.basket.bo.Equipe;
import fr.eni.basket.dto.EquipeDTO;
import fr.eni.basket.exceptions.EchecAjoutEquipe;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EquipeServiceTest {

    @Autowired
    private EquipeServiceTestImpl equipeService;


    @Test
    @DisplayName("test getAllEquipes Cas : des equipes existent")
    public void testGetAllEquipesCasDesEquipesExistent()
    {
        //AAA
        //Arrange = Préparation du test
        List<Equipe> equipes = new ArrayList<>();
        equipes.add(new Equipe(1, "U15F1"));
        equipes.add(new Equipe(2, "U15F2"));
        equipes.add(new Equipe(3, "U18M1"));
        equipeService.setEquipes(equipes);

        //Act
        List<Equipe> resultat = equipeService.getEquipes();

        //Assert
        assertEquals(equipes.size(), resultat.size());

    }

    @Test
    @DisplayName("test d'ajout d'une équipe dans le cas de données valides ")
    public void testAjoutEquipeCasDonneesValides()
    {
        //AAA
        //Arrange = Préparation du test
        List<Equipe> equipes = new ArrayList<>();
        equipeService.setEquipes(equipes);
        EquipeDTO equipeDTO = new EquipeDTO("U15F1");

        //Act
        Equipe equipe = equipeService.addEquipe(equipeDTO);

        //Assert
        assertNotNull(equipe);
        assertEquals(equipe.getNom(), equipeDTO.nom());
        assertTrue(equipe.getNoEquipe()>0);

    }

    @Test
    @DisplayName("Test ajout equipe cas échec de l'ajout")
    public void testAjoutEquipeCasDonneesInvalides(){
        //AAA
        //Arrange = Préparation du test
        List<Equipe> equipes = new ArrayList<>();
        equipeService.setEquipes(equipes);
        EquipeDTO equipeDTO = new EquipeDTO("equipe1");
        Equipe equipe = equipeService.addEquipe(equipeDTO);

        //Act + Assert
        assertThrows(EchecAjoutEquipe.class, ()-> equipeService.addEquipe(equipeDTO));


    }



}
