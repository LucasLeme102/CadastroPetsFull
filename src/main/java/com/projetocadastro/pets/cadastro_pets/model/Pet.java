package com.projetocadastro.pets.cadastro_pets.model;


import com.projetocadastro.pets.cadastro_pets.model.enums.TipoPet;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "pets")
@Getter
@Setter
@NoArgsConstructor

public class Pet  {

    @Id
    @GeneratedValue
    private UUID id;
    private String  nome;
    private Integer idade;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 20)
    private TipoPet tipo;
    private String raca;
    private Double peso;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;
    @Column(name = "criado_em ")
    private LocalDateTime criadoEm = LocalDateTime.now();
    @ManyToOne(optional = false)
    @JoinColumn(name = "tutor_id",nullable = false)
    private Tutor tutor;
    public Pet(String nome, Integer idade, TipoPet tipo, String raca, Double peso, Endereco endereco, LocalDateTime criadoEm) {
        this.nome = nome;
        this.idade = idade;
        this.tipo = tipo;
        this.raca = raca;
        this.peso = peso;
        this.endereco = endereco;
        this.criadoEm = criadoEm;
    }
}
