package com.projetocadastro.pets.cadastro_pets.repositories;

import com.projetocadastro.pets.cadastro_pets.model.enums.TipoPet;
import com.projetocadastro.pets.cadastro_pets.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface PetRepository extends JpaRepository<Pet,UUID>{
    List<Pet> findBytipo(TipoPet tipo); // CORRIGIDO
    List<Pet> findByNomeContainingIgnoreCase(String nome);
    List<Pet> findByEndereco_Cidade_ContainingIgnoreCase(String cidade);
    List<Pet> findByRacaContainingIgnoreCase(String raca);
    List<Pet> findByIdade(Integer idade);
    List<Pet> findByPeso(Double peso);

}
