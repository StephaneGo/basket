package fr.eni.basket.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record JoueurDTO(@NotBlank @Size(max = 30) String nom, @NotBlank @Size(max = 30) String prenom, @Email String email, int noEquipe) {
}
