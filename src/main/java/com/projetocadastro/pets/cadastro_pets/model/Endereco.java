package com.projetocadastro.pets.cadastro_pets.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "enderecos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Endereco {
    @Id
    @GeneratedValue
    private UUID id;


    @NotBlank
    @Size(max = 100)
    private String rua;

    @NotBlank
    @Size(max = 10)
    private String numero;

    @NotBlank
    @Size(max = 50)
    private String cidade;

    @NotBlank
    @Size(max = 2, message = "Use a sigla do estado, ex: SP")
    private String estado;



}
