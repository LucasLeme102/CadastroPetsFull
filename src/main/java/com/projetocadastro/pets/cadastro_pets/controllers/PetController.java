package com.projetocadastro.pets.cadastro_pets.controllers;


import com.projetocadastro.pets.cadastro_pets.dtos.PetRequestDto;
import com.projetocadastro.pets.cadastro_pets.dtos.PetResponseDto;
import com.projetocadastro.pets.cadastro_pets.model.Pet;
import com.projetocadastro.pets.cadastro_pets.services.PetService;
import com.projetocadastro.pets.cadastro_pets.utils.PetMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private PetMapper petMapper;

    @PostMapping
    public ResponseEntity<EntityModel<PetResponseDto>> cadastrar(@RequestBody @Valid PetRequestDto dto){
        PetResponseDto response = petService.cadastrar(dto);

        EntityModel<PetResponseDto> resource = EntityModel.of(response,
                linkTo(methodOn(TutorController.class).buscarPorIdDoTutor(response.tutor().id())).withRel("tutor")
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<PetResponseDto>>>  listarTodos(){
        List<PetResponseDto> pets = petService.listarTodos();
        List<EntityModel<PetResponseDto>> petsComLinks = pets.stream()
                .map(pet -> EntityModel.of(pet,
                        linkTo(methodOn(TutorController.class).buscarPorIdDoTutor(pet.tutor().id())).withRel("tutor")
                )).toList();
        return ResponseEntity.ok(
                CollectionModel.of(petsComLinks,linkTo(methodOn(PetController.class).listarTodos()).withSelfRel()));

    }



    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<PetResponseDto>> buscarPorId(@PathVariable UUID id){
        Pet pet = petService.buscarPorId(id);
        PetResponseDto response = PetMapper.toDto(pet);

        EntityModel<PetResponseDto> resource = EntityModel.of(response);

        resource.add(linkTo(methodOn(TutorController.class).buscarPorIdDoTutor(pet.getTutor().getId())).withRel("tutor"));


        return ResponseEntity.ok(resource);
    }






    @PutMapping("/{id}")
    public ResponseEntity<EntityModel<PetResponseDto>> atualizar(@PathVariable UUID id,@RequestBody @Valid PetRequestDto dto){
        PetResponseDto atualizado = petService.alterar(id, dto);
        EntityModel<PetResponseDto> resource = EntityModel.of(atualizado,
                linkTo(methodOn(TutorController.class).buscarPorIdDoTutor(atualizado.tutor().id())).withRel("tutor")
        );
        return ResponseEntity.ok(resource);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id){
        petService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
