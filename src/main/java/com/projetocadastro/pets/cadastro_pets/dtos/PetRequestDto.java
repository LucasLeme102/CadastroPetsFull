package com.projetocadastro.pets.cadastro_pets.dtos;

import com.projetocadastro.pets.cadastro_pets.enums.TipoPet;


import java.util.UUID;

public record PetRequestDto(
        String nome,
        Integer idade,
        TipoPet tipo,
        String raca,
        Double peso,
        EnderecoRequestDTO endereco,
        UUID tutorId
) {}
