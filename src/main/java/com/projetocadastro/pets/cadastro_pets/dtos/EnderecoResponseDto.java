package com.projetocadastro.pets.cadastro_pets.dtos;

public record EnderecoResponseDto(
        String rua,
        String numero,
        String cidade,
        String estado
) {}
