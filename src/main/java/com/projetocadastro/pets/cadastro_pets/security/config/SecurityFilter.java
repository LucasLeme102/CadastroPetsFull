package com.projetocadastro.pets.cadastro_pets.security.config;

import com.projetocadastro.pets.cadastro_pets.repositories.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService service;
    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.recover(request);
        if (token != null) {
            var subject = service.validateToken(token);
            if (subject != null) {
                UserDetails user = repository.findByUsername(subject);
                if (user != null) {
                    var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recover(HttpServletRequest request){
        var headerAuthortization = request.getHeader("Authorization");
        if (headerAuthortization != null && headerAuthortization.startsWith("Bearer ")) {
            return headerAuthortization.substring(7).trim();
        }
        return null;
    }
}