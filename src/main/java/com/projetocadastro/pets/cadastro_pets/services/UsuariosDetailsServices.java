package com.projetocadastro.pets.cadastro_pets.services;

import com.projetocadastro.pets.cadastro_pets.model.Usuario;
import com.projetocadastro.pets.cadastro_pets.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuariosDetailsServices implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("usuário nao encontrado"));
        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles("USER")
                .build();



    }
}
