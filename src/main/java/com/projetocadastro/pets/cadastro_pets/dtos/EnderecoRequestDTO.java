package com.projetocadastro.pets.cadastro_pets.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EnderecoRequestDTO(
        String rua,
        String numero,
        String cidade,
        String estado
) {}