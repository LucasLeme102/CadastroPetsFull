package com.projetocadastro.pets.cadastro_pets.repositories;

import com.projetocadastro.pets.cadastro_pets.model.Pet;
import com.projetocadastro.pets.cadastro_pets.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface TutorRepository extends JpaRepository<Tutor, UUID> {

    Boolean existsByNomeIgnoreCase(String nome);
}
