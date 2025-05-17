package com.projetocadastro.pets.cadastro_pets.controllers;

import com.projetocadastro.pets.cadastro_pets.dtos.TutorRequestDto;
import com.projetocadastro.pets.cadastro_pets.dtos.TutorResponseDto;
import com.projetocadastro.pets.cadastro_pets.model.Pet;
import com.projetocadastro.pets.cadastro_pets.model.Tutor;
import com.projetocadastro.pets.cadastro_pets.services.TutorService;
import com.projetocadastro.pets.cadastro_pets.utils.TutorMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/tutores")
@RequiredArgsConstructor
public class TutorController {
    @Autowired
    private TutorService service;

    @Autowired
    private TutorMapper mapper;

    @PostMapping
    public ResponseEntity<EntityModel<TutorResponseDto>> criarTutor(@RequestBody @Valid TutorRequestDto dto){
        TutorResponseDto tutorResponseDto = service.criarTutor(dto);

        EntityModel resource = EntityModel.of(tutorResponseDto,
                linkTo(methodOn(PetController.class).listarTodos()).withRel("pets") // opcional
        );



        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<TutorResponseDto>> buscarPorIdDoTutor(@PathVariable UUID id){
        Tutor tutor = service.buscarTutorPorId(id);
        TutorResponseDto dto = mapper.toDto(tutor);

        EntityModel<TutorResponseDto> resource = EntityModel.of(dto);
        resource.add(linkTo(methodOn(TutorController.class).buscarPorIdDoTutor(id)).withSelfRel());

        for(Pet pet : tutor.getPets()){
            resource.add(linkTo(methodOn(PetController.class).buscarPorId(pet.getId())).withRel("pet-"+pet.getId()));
        }
        return ResponseEntity.ok(resource);


    }
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<TutorResponseDto>>> buscarTutores(){
        List<TutorResponseDto> tutores = service.listarTodosTutores();

        List<EntityModel<TutorResponseDto>> tutoresComLinks = tutores.stream().map(tutor -> EntityModel.of(
                tutor,
                linkTo(methodOn(PetController.class).listarTodos()).withRel("pets")
        )).toList();
        return ResponseEntity.ok(
                CollectionModel.of(tutoresComLinks,
                        linkTo(methodOn(TutorController.class).buscarTutores()).withSelfRel()
                ));
    }

}
