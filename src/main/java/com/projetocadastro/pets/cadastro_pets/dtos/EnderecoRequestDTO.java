package com.projetocadastro.pets.cadastro_pets.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record EnderecoRequestDTO(
        @NotBlank(message = "Rua é obrigatória")
        String rua,
        @Positive(message = "O número deve ser positivo")
        @NotBlank(message = "Número é obrigatório")
        String numero,
        @NotBlank(message = "Cidade é obrigatória")
        String cidade,
        @NotBlank(message = "Estado é obrigatório")
        @Size(min = 2, max = 2, message = "O estado deve ter 2 letras (sigla)")
        String estado
) {}