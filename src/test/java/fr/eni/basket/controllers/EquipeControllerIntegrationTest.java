package fr.eni.basket.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.eni.basket.dto.EquipeDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
class EquipeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    @DisplayName("Test d'ajout d'une équipe cas données valides")
    void testAddEquipe_Success() throws Exception {
        // préparation des données
        EquipeDTO equipeDto = new EquipeDTO("U13M2");


        // exécution du test
        mockMvc.perform(post("/equipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(equipeDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nom").value("U13M2"));
    }

    @Test
    void testAddEquipe_ValidationError() throws Exception {
        // DTO invalide (nom nul ou vide)
        EquipeDTO invalidEquipe = new EquipeDTO("");


        mockMvc.perform(post("/equipes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidEquipe)))
                .andExpect(status().isBadRequest());
    }
}

