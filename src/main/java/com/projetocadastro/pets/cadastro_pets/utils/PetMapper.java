package com.projetocadastro.pets.cadastro_pets.utils;

import com.projetocadastro.pets.cadastro_pets.dtos.PetRequestDto;
import com.projetocadastro.pets.cadastro_pets.dtos.PetResponseDto;
import com.projetocadastro.pets.cadastro_pets.model.Endereco;
import com.projetocadastro.pets.cadastro_pets.model.Pet;
import com.projetocadastro.pets.cadastro_pets.model.Tutor;
import org.springframework.stereotype.Component;
@Component
public class PetMapper {

    public static Pet toEntity(PetRequestDto dto, Tutor tutor){
        if (dto == null){throw new IllegalArgumentException("Informações que foram passadas estão erradas ");}
        Pet pet = new Pet();
        pet.setNome(dto.nome());
        pet.setIdade(dto.idade());
        pet.setTipo(dto.tipo());
        pet.setRaca(dto.raca());
        pet.setPeso(dto.peso());
        pet.setTutor(tutor);
        Endereco endereco = new Endereco();
        endereco.setRua(dto.endereco().rua());
        endereco.setNumero(dto.endereco().numero());
        endereco.setCidade(dto.endereco().cidade());
        endereco.setEstado(dto.endereco().estado());
        pet.setEndereco(endereco);
        return pet;
    }
    public static PetResponseDto toDto(Pet pet){
        return new PetResponseDto(
                pet.getId(),
                pet.getNome(),
                pet.getIdade(),
                pet.getTipo(),
                pet.getRaca(),
                pet.getPeso(),
                EnderecoMapper.toResponseDTO(pet.getEndereco()),
                TutorMapper.toDto(pet.getTutor()),
                pet.getCriadoEm()
        );
    }
}
