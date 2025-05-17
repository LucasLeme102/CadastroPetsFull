package com.projetocadastro.pets.cadastro_pets.model;


import com.projetocadastro.pets.cadastro_pets.enums.TipoPet;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

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

    @NotBlank(message = "Nome completo do pet é obrigatório")
    @Pattern(regexp = "^[A-Za-zA-ÿ]+(\\s[A-Za-zA-ÿ]+)+$", message = "Só deve conter letras e acentuações necessárias")
    private String  nome;


    @NotNull(message = "Idade é Obrigatória")
    @Max(value = 22, message = "A idade máxima do seu pet é 22")
    private Integer idade;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 20)
    private TipoPet tipo;

    @NotBlank(message = "Raça é obrigatório")
    private String raca;

    @DecimalMin(value = "0.1", message = "O peso deve ser maior que 0")
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
