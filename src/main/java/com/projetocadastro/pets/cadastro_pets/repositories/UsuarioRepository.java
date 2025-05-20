package com.projetocadastro.pets.cadastro_pets.repositories;

import com.projetocadastro.pets.cadastro_pets.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {

    UserDetails findByUsername(String username);



}
