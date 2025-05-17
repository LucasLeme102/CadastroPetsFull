package com.projetocadastro.pets.cadastro_pets.dtos;

import jakarta.validation.constraints.NotBlank;

public record TutorRequestDto (
        @NotBlank String nome


){}
