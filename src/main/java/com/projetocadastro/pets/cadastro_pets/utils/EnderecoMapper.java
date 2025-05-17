package com.projetocadastro.pets.cadastro_pets.utils;

import com.projetocadastro.pets.cadastro_pets.dtos.EnderecoRequestDTO;
import com.projetocadastro.pets.cadastro_pets.dtos.EnderecoResponseDto;
import com.projetocadastro.pets.cadastro_pets.model.Endereco;

public class EnderecoMapper {

    public static Endereco toEntity(EnderecoRequestDTO enderecoRequestDTO){
        if(enderecoRequestDTO == null)return null;
        Endereco endereco = new Endereco();
        endereco.setRua(enderecoRequestDTO.rua());
        endereco.setNumero(enderecoRequestDTO.numero());
        endereco.setCidade(enderecoRequestDTO.cidade());
        endereco.setEstado(enderecoRequestDTO.estado());
        return endereco;
    }

    public static EnderecoResponseDto toResponseDTO(Endereco endereco){
        if (endereco == null) return null;
        return new EnderecoResponseDto(
                endereco.getRua(),
                endereco.getNumero(),
                endereco.getCidade(),
                endereco.getEstado()
        );
    }
}
