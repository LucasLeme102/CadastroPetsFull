package com.projetocadastro.pets.cadastro_pets.dtos;

import com.projetocadastro.pets.cadastro_pets.model.enums.Roles;

public record RegisterDto(String username, String password, Roles role) {
}
