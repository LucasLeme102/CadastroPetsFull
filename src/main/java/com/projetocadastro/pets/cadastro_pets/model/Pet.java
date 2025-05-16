package com.projetocadastro.pets.cadastro_pets.model;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "pets")
@Getter
@Setter
public class Pet {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank(message = "O nome do pet é obrigatório")
    @Size(max = 100)
    private String  nome;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false,length = 20)
    private TipoPet tipo;

    @Size(max = 100)
    private String raca;

    @DecimalMin(value = "0.1", message = "O peso deve ser maior que 0")
    private BigDecimal peso;

    @Embedded
    @Valid
    private Endereco endereco;

    @Column(name = "Criado em ")
    private LocalDateTime criadoEm = LocalDateTime.now();

}
