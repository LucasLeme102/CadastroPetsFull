package com.projetocadastro.pets.cadastro_pets.services;

import com.projetocadastro.pets.cadastro_pets.dtos.TutorRequestDto;
import com.projetocadastro.pets.cadastro_pets.dtos.TutorResponseDto;
import com.projetocadastro.pets.cadastro_pets.exceptions.RecursoDuplicadoException;
import com.projetocadastro.pets.cadastro_pets.model.Tutor;
import com.projetocadastro.pets.cadastro_pets.repositories.TutorRepository;
import com.projetocadastro.pets.cadastro_pets.utils.PetMapper;
import com.projetocadastro.pets.cadastro_pets.utils.TutorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TutorService {

    @Autowired
    private TutorRepository repository;


    public TutorResponseDto criarTutor(TutorRequestDto dto){
        if(repository.existsByNomeIgnoreCase(dto.nome())){
            throw new RecursoDuplicadoException("Já exite um tutor com esse nome!");
        }
        Tutor tutor = new Tutor(dto.nome());
        repository.save(tutor);
        //return new TutorResponseDto(tutor.getId(),tutor.getNome());
        return TutorMapper.toDto(tutor);

    }

    public Tutor buscarTutorPorId(UUID id){
        Tutor tutor = repository.findById(id).orElseThrow(() -> new RuntimeException("Tutor não encontrado"));
        return tutor;
    }

    public List<TutorResponseDto> listarTodosTutores(){
        List<Tutor> all = repository.findAll();
        List<TutorResponseDto> todosEmDto = new ArrayList<>();
        for(Tutor x : all ){
            TutorResponseDto dto = TutorMapper.toDto(x);
            todosEmDto.add(dto);
        }
        return todosEmDto;
    }
}
