package com.projetocadastro.pets.cadastro_pets.controllers;


import com.projetocadastro.pets.cadastro_pets.dtos.PetRequestDto;
import com.projetocadastro.pets.cadastro_pets.dtos.PetResponseDto;
import com.projetocadastro.pets.cadastro_pets.enums.TipoPet;
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
    @GetMapping("/cidade/{cidade}")
    public ResponseEntity<CollectionModel<EntityModel<PetResponseDto>>> listarTodosPorCidade(@PathVariable String cidade){
        List<PetResponseDto> petsdto = petService.buscarPorCidade(cidade);
        List<EntityModel<PetResponseDto>> petLinks = petsdto.stream()
                .map(pet -> EntityModel.of(pet,
                        linkTo(methodOn(TutorController.class).buscarPorIdDoTutor(pet.tutor().id())).withSelfRel())).toList();

        return ResponseEntity.ok(
                CollectionModel.of(petLinks,linkTo(methodOn(PetController.class).listarTodosPorCidade(cidade)).withSelfRel())
        );
    }
    @GetMapping("/raca/{raca}")
    public ResponseEntity<CollectionModel<EntityModel<PetResponseDto>>> listarTodosPorRaca(@PathVariable String raca){
        List<PetResponseDto> petsdto = petService.buscarPorRaca(raca);
        List<EntityModel<PetResponseDto>> petLinks = petsdto.stream()
                .map(pet -> EntityModel.of(pet,
                        linkTo(methodOn(TutorController.class).buscarPorIdDoTutor(pet.tutor().id())).withSelfRel())).toList();

        return ResponseEntity.ok(
                CollectionModel.of(petLinks,linkTo(methodOn(PetController.class).listarTodosPorRaca(raca)).withSelfRel())
        );
    }
    @GetMapping("/peso/{peso}")
    public ResponseEntity<CollectionModel<EntityModel<PetResponseDto>>>listarTodosPorPeso(@PathVariable Double peso){
        List<PetResponseDto> petsdto = petService.buscarPorPeso(peso);
        List<EntityModel<PetResponseDto>> petLinks = petsdto.stream()
                .map(pet -> EntityModel.of(pet,
                        linkTo(methodOn(TutorController.class).buscarPorIdDoTutor(pet.tutor().id())).withSelfRel())).toList();

        return ResponseEntity.ok(
                CollectionModel.of(petLinks,linkTo(methodOn(PetController.class).listarTodosPorPeso(peso)).withSelfRel())
        );
    }
    @GetMapping("/idade/{idade}")
    public ResponseEntity<CollectionModel<EntityModel<PetResponseDto>>> listarTodosPorIdade(@PathVariable Integer idade){
        List<PetResponseDto> petsdto = petService.buscarPorIdade(idade);
        List<EntityModel<PetResponseDto>> petLinks = petsdto.stream()
                .map(pet -> EntityModel.of(pet,
                        linkTo(methodOn(TutorController.class).buscarPorIdDoTutor(pet.tutor().id())).withSelfRel())).toList();

        return ResponseEntity.ok(
                CollectionModel.of(petLinks,linkTo(methodOn(PetController.class).listarTodosPorIdade(idade)).withSelfRel())
        );
    }
    @GetMapping("/nomeOuSobrenome/{nomeOuSobrenome}")
    public ResponseEntity<CollectionModel<EntityModel<PetResponseDto>>> listarTodosPorNomeOuSobrenome(@PathVariable String nomeOuSobrenome){
        List<PetResponseDto> petsdto = petService.buscarNomeOuSobrenome(nomeOuSobrenome);
        List<EntityModel<PetResponseDto>> petLinks = petsdto.stream()
                .map(pet -> EntityModel.of(pet,
                        linkTo(methodOn(TutorController.class).buscarPorIdDoTutor(pet.tutor().id())).withSelfRel())).toList();

        return ResponseEntity.ok(
                CollectionModel.of(petLinks,linkTo(methodOn(PetController.class).listarTodosPorNomeOuSobrenome(nomeOuSobrenome)).withSelfRel())
        );
    }
    @GetMapping("/tipo/{tipoPet}")
    public ResponseEntity<CollectionModel<EntityModel<PetResponseDto>>> listarTodosPorTipo(@PathVariable String tipoPet){
        List<PetResponseDto> petsdto = petService.buscarPorTipo(tipoPet);
        List<EntityModel<PetResponseDto>> petLinks = petsdto.stream()
                .map(pet -> EntityModel.of(pet,
                        linkTo(methodOn(TutorController.class).buscarPorIdDoTutor(pet.tutor().id())).withSelfRel())).toList();

        return ResponseEntity.ok(
                CollectionModel.of(petLinks,linkTo(methodOn(PetController.class).listarTodosPorTipo(tipoPet)).withSelfRel())
        );
    }






}
