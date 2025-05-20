package com.projetocadastro.pets.cadastro_pets.dtos;

import com.projetocadastro.pets.cadastro_pets.model.enums.TipoPet;

import java.time.LocalDateTime;
import java.util.UUID;

public record PetResponseDto(   UUID id,
                                String nome,
                                Integer idade,
                                TipoPet tipo,
                                String raca,
                                Double peso,
                                EnderecoResponseDto endereco,
                                TutorResponseDto tutor,
                                LocalDateTime criadoEm
) {}
