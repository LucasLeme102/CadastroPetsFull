package com.projetocadastro.pets.cadastro_pets.dtos;

import java.util.UUID;

public record TutorResponseDto(
        UUID id,
        String nome,
        String telefone,
        String email
) {
}
