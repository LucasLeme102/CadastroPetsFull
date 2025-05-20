package com.projetocadastro.pets.cadastro_pets.controllers;

import com.projetocadastro.pets.cadastro_pets.dtos.LoginDtoRequest;
import com.projetocadastro.pets.cadastro_pets.dtos.LoginDtoResponse;
import com.projetocadastro.pets.cadastro_pets.dtos.RegisterDto;
import com.projetocadastro.pets.cadastro_pets.model.Usuario;
import com.projetocadastro.pets.cadastro_pets.repositories.UsuarioRepository;
import com.projetocadastro.pets.cadastro_pets.security.config.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService service;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginDtoRequest dto){
        var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(dto.username(),dto.password());
        var auth = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        var token = service.generateToken((Usuario)auth.getPrincipal());

        return ResponseEntity.ok(new LoginDtoResponse(token));
    }


    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDto registerDto){
        if(this.repository.findByUsername(registerDto.username()) != null) return ResponseEntity.badRequest().build();
        String senhaCriptografada = new BCryptPasswordEncoder().encode(registerDto.password());
        Usuario usuario = new Usuario(registerDto.username(),senhaCriptografada,registerDto.role());

        Usuario save = repository.save(usuario);
        return ResponseEntity.ok(save);
    }
}
