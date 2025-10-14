package fr.eni.basket.bll;


import fr.eni.basket.bo.Equipe;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EquipeServiceTest {

    @Autowired
    private EquipeServiceTestImpl equipeService;


    @Test
    @DisplayName("test getAllEquipes Cas : des equipes existent")
    public void testGetAllEquipesCasDesEquipesExistent()
    {
        //Arrange
        List<Equipe> equipes = new ArrayList<>();
        equipes.add(new Equipe(1, "U15F1"));
        equipes.add(new Equipe(2, "U15F2"));
        equipes.add(new Equipe(3, "U18M1"));
        equipeService.setEquipes(equipes);

        //Act
        List<Equipe> resultat = equipeService.getEquipes();

        //Assert
        assertEquals(resultat.size(), 3);


    }

}
