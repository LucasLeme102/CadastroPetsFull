package com.projetocadastro.pets.cadastro_pets.controllers;

import com.projetocadastro.pets.cadastro_pets.model.Usuario;
import com.projetocadastro.pets.cadastro_pets.security.JwtUtil;
import com.projetocadastro.pets.cadastro_pets.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioServices usuarioServices;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String,String> request){
        Usuario usuario = usuarioServices.registrarUsuario(request.get("username"), "password");
        return ResponseEntity.ok(usuario);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String,String> request){
        Optional<Usuario> usuario = usuarioServices.buscarPorUser(request.get("username"));
        if(usuario.isPresent() && usuario.get().getPassword().equals(request.get("password"))){
            String token = JwtUtil.generateToken(usuario.get().getUsername());
            return ResponseEntity.ok(Map.of("token",token));
        }
        return ResponseEntity.status(401).body("Credenciais inválidas");
    }


}
