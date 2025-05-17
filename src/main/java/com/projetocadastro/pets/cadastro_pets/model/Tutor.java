package com.projetocadastro.pets.cadastro_pets.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tutores")
@Getter
@Setter
@NoArgsConstructor
public class Tutor {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank(message = "Nome do tutor é obrigatório")
    private String nome;

    @OneToMany(mappedBy = "tutor", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Pet> pets = new ArrayList<>();

    public Tutor(String nome) {
        this.nome = nome;

    }
}
