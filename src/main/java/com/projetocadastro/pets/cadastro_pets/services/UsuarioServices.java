package com.projetocadastro.pets.cadastro_pets.services;

import com.projetocadastro.pets.cadastro_pets.model.Usuario;
import com.projetocadastro.pets.cadastro_pets.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServices {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServices(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Usuario registrarUsuario(String username, String password){
        String senhaCriptografada = passwordEncoder.encode(password);
        Usuario usuario = new Usuario(username,senhaCriptografada);
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> buscarPorUser(String username){
        return usuarioRepository.findByUsername(username);
    }

}
