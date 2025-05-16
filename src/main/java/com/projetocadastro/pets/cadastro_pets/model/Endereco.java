package com.projetocadastro.pets.cadastro_pets.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter

public class Endereco {
    @NotBlank
    @Size(max = 100)
    private String rua;

    @NotBlank
    @Size(max = 10)
    private String numero;

    @Size(max = 50)
    private String complemento;

    @NotBlank
    @Size(max = 50)
    private String cidade;

    @NotBlank
    @Size(max = 2, message = "Use a sigla do estado, ex: SP")
    private String estado;

}
