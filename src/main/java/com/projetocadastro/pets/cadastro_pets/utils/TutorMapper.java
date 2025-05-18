package com.projetocadastro.pets.cadastro_pets.utils;

import com.projetocadastro.pets.cadastro_pets.dtos.TutorRequestDto;
import com.projetocadastro.pets.cadastro_pets.dtos.TutorResponseDto;

import com.projetocadastro.pets.cadastro_pets.model.Tutor;
import org.springframework.stereotype.Component;
@Component
public class TutorMapper {

    public static Tutor toEntity(TutorRequestDto dto){
        if(dto == null){throw new IllegalArgumentException("Informacoes passadas erradas");}
        Tutor tutor = new Tutor();
        tutor.setNome(dto.nome());
        tutor.setTelefone(dto.telefone());
        tutor.setEmail(dto.email());
        return tutor;

    }
    public static TutorResponseDto toDto(Tutor tutor){
        if(tutor == null){throw new IllegalArgumentException("Informacoes passadas erradas");}

        return new TutorResponseDto(
                tutor.getId(),
                tutor.getNome(),
                tutor.getTelefone(),
                tutor.getEmail()
        );
    }

}
