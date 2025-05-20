package com.projetocadastro.pets.cadastro_pets.dtos;

import com.projetocadastro.pets.cadastro_pets.model.enums.TipoPet;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;


import java.util.UUID;

public record PetRequestDto(
        @NotBlank(message = "Nome completo do pet é obrigatório")
        @Pattern(regexp = "^[A-Za-zÀ-ú]+(\\s[A-Za-zÀ-ú]+)+$", message = "Informe o nome completo com sobrenome do pet")
        String nome,

        @NotNull(message = "Idade é obrigatória")
        @Min(value = 0, message = "A idade mínima permitida é 0")
        @Max(value = 22, message = "A idade máxima permitida é 22")
        Integer idade,

        @NotNull(message = "Tipo do pet é obrigatório")
        TipoPet tipo,

        @NotBlank(message = "Raça do pet é obrigatória")
        String raca,

        @NotNull(message = "Peso é obrigatório")
        @DecimalMax(value = "60.0", message = "O peso maximo deve ser 60KG")
        @DecimalMin(value = "0.1", message = "O peso deve ser maior que 0")
        Double peso,

        @Valid
        @NotNull(message = "Endereço é obrigatório")
        EnderecoRequestDTO endereco,

        @Valid
        @NotNull(message = "Tutor é obrigatório")
        UUID tutorId
) {}
