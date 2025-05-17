package com.projetocadastro.pets.cadastro_pets.services;

import com.projetocadastro.pets.cadastro_pets.dtos.PetRequestDto;
import com.projetocadastro.pets.cadastro_pets.dtos.PetResponseDto;
import com.projetocadastro.pets.cadastro_pets.enums.TipoPet;
import com.projetocadastro.pets.cadastro_pets.exceptions.ResourceNotFoundExceptions;
import com.projetocadastro.pets.cadastro_pets.model.Pet;
import com.projetocadastro.pets.cadastro_pets.model.Tutor;
import com.projetocadastro.pets.cadastro_pets.repositories.PetRepository;
import com.projetocadastro.pets.cadastro_pets.repositories.TutorRepository;
import com.projetocadastro.pets.cadastro_pets.utils.PetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;

    @Autowired
    private TutorRepository tutorRepository;

    public PetResponseDto cadastrar(PetRequestDto petdto){
        Tutor tutor = tutorRepository.findById(petdto.tutorId()).orElseThrow(() -> new ResourceNotFoundExceptions("Tutor não encontrado"));
        Pet pet = PetMapper.toEntity(petdto,tutor);
        Pet save = petRepository.save(pet);
        return PetMapper.toDto(save);

    }

    public List<PetResponseDto> listarTodos(){
        List<PetResponseDto> list = petRepository.findAll()
                .stream()
                .map(PetMapper::toDto)
                .toList();
        if(list.isEmpty()){
            throw new ResourceNotFoundExceptions("Nenhum pet salvo");
        }
        return list;
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


    public List<PetResponseDto> buscarPorCidade(String cidade){
        List<Pet> byEnderecoCidade = petRepository.findByEndereco_Cidade_ContainingIgnoreCase(cidade);
        if(byEnderecoCidade.isEmpty()){
            throw new ResourceNotFoundExceptions("Sem pets nessa cidade");
        }
        List<PetResponseDto> responseDtos = new ArrayList<>();

        for(Pet x : byEnderecoCidade){

            responseDtos.add(PetMapper.toDto(x));
        }

        return responseDtos;
    }
    public List<PetResponseDto> buscarPorRaca(String raca){
        List<Pet> byRaca = petRepository.findByRacaContainingIgnoreCase(raca);
        if(byRaca.isEmpty()){
            throw new ResourceNotFoundExceptions("Sem pets desta raça");
        }
        List<PetResponseDto> responseDtos = new ArrayList<>();

        for(Pet x : byRaca){

            responseDtos.add(PetMapper.toDto(x));
        }

        return responseDtos;
    }
    public List<PetResponseDto> buscarPorPeso(Double peso){
        List<Pet> byPeso = petRepository.findByPeso(peso);
        if(byPeso.isEmpty()){
            throw new ResourceNotFoundExceptions("Sem pets desse peso");
        }
        List<PetResponseDto> responseDtos = new ArrayList<>();

        for(Pet x : byPeso){

            responseDtos.add(PetMapper.toDto(x));
        }

        return responseDtos;
    }
    public List<PetResponseDto> buscarPorIdade(Integer idade){
        List<Pet> byIdade = petRepository.findByIdade(idade);
        if(byIdade.isEmpty()){
            throw new ResourceNotFoundExceptions("Sem pets desta idade");
        }
        List<PetResponseDto> responseDtos = new ArrayList<>();

        for(Pet x : byIdade){

            responseDtos.add(PetMapper.toDto(x));
        }

        return responseDtos;
    }
    public List<PetResponseDto> buscarNomeOuSobrenome(String nomeOuSobrenome){
        List<Pet> byNomeContainingIgnoreCase = petRepository.findByNomeContainingIgnoreCase(nomeOuSobrenome);
        if(byNomeContainingIgnoreCase.isEmpty()){
            throw new ResourceNotFoundExceptions("Sem pets com esse nome/sobrenome");
        }
        List<PetResponseDto> responseDtos = new ArrayList<>();

        for(Pet x : byNomeContainingIgnoreCase){

            responseDtos.add(PetMapper.toDto(x));
        }

        return responseDtos;
    }
    public List<PetResponseDto> buscarPorTipo(
    String tipoPetString) {
            TipoPet tipoPet = TipoPet.valueOf(tipoPetString.toUpperCase());
            List<Pet> pets = petRepository.findBytipo(tipoPet);
            return pets.stream().map(PetMapper::toDto).toList();


    }



}
