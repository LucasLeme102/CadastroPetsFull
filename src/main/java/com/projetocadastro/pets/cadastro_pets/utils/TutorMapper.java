package com.projetocadastro.pets.cadastro_pets.utils;

import com.projetocadastro.pets.cadastro_pets.controllers.PetController;
import com.projetocadastro.pets.cadastro_pets.controllers.TutorController;
import com.projetocadastro.pets.cadastro_pets.dtos.TutorRequestDto;
import com.projetocadastro.pets.cadastro_pets.dtos.TutorResponseDto;
import com.projetocadastro.pets.cadastro_pets.model.Pet;
import com.projetocadastro.pets.cadastro_pets.model.Tutor;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
@Component
public class TutorMapper {

    public static Tutor toEntity(TutorRequestDto dto){
        if(dto == null)return null;
        Tutor tutor = new Tutor();
        tutor.setNome(dto.nome());
        tutor.setTelefone(dto.telefone());
        tutor.setEmail(dto.email());
        return tutor;

    }
    public static TutorResponseDto toDto(Tutor tutor){
        if(tutor == null)return null;
        return new TutorResponseDto(
                tutor.getId(),
                tutor.getNome(),
                tutor.getTelefone(),
                tutor.getEmail()
        );
    }

}
