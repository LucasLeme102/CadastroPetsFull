package com.projetocadastro.pets.cadastro_pets.services;

import com.projetocadastro.pets.cadastro_pets.dtos.PetRequestDto;
import com.projetocadastro.pets.cadastro_pets.dtos.PetResponseDto;
import com.projetocadastro.pets.cadastro_pets.exceptions.ResourceNotFoundExceptions;
import com.projetocadastro.pets.cadastro_pets.model.Pet;
import com.projetocadastro.pets.cadastro_pets.model.Tutor;
import com.projetocadastro.pets.cadastro_pets.repositories.PetRepository;
import com.projetocadastro.pets.cadastro_pets.repositories.TutorRepository;
import com.projetocadastro.pets.cadastro_pets.utils.PetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private TutorRepository tutorRepository;

    public PetResponseDto cadastrar(PetRequestDto petdto){
        Tutor tutor = tutorRepository.findById(petdto.tutorId()).orElseThrow(() -> new RuntimeException("Tutor não encontrado"));
        Pet pet = PetMapper.toEntity(petdto,tutor);
        Pet save = petRepository.save(pet);
        return PetMapper.toDto(save);

    }

    public List<PetResponseDto> listarTodos(){
        return petRepository.findAll()
                .stream()
                .map(PetMapper::toDto)
                .toList();
    }

    public Pet buscarPorId(UUID id){
       Pet pet = petRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundExceptions("Pet não encontrado"));
       return pet;
    }

    public PetResponseDto alterar(UUID id, PetRequestDto dto){
        Pet petExistente = petRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundExceptions("Pet não encontrado"));


        Tutor tutor = tutorRepository.findById(dto.tutorId()).orElseThrow(() -> new ResourceNotFoundExceptions("Tutor não encontrado"));

        petExistente.setNome(dto.nome());
        petExistente.setTipo(dto.tipo());
        petExistente.setPeso(dto.peso());
        petExistente.setIdade(dto.idade());
        petExistente.setRaca(dto.raca());
        petExistente.getEndereco().setCidade(dto.endereco().cidade());
        petExistente.getEndereco().setRua(dto.endereco().rua());
        petExistente.getEndereco().setNumero(dto.endereco().numero());

        petExistente.setTutor(tutor);
        Pet save = petRepository.save(petExistente);
        return PetMapper.toDto(save);
    }

    public void deletar(UUID id){
        if(!petRepository.existsById(id)){
            throw new ResourceNotFoundExceptions("pet com id : "+ id + " não existe");
        }
        petRepository.deleteById(id);
    }




}
