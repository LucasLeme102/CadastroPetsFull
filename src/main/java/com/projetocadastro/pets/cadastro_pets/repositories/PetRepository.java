package com.projetocadastro.pets.cadastro_pets.repositories;

import com.projetocadastro.pets.cadastro_pets.model.Pet;
import com.projetocadastro.pets.cadastro_pets.model.TipoPet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.*;
import java.util.List;
import java.util.UUID;
@Repository
public interface PetRepository extends JpaRepository<Pet,UUID>{
    List<Pet> findByTipo(TipoPet tipoPet);
    List<Pet> findByNomeContainingIgnoreCase(String nome);
    List<Pet> findByCidade(String cidade);

}
