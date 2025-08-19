package com.foro.forohub.security;

import com.foro.forohub.service.TokenService; // Importación correcta
import com.foro.forohub.repository.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var authHeader = request.getHeader("Authorization");
        System.out.println("Header de autorización recibido: " + authHeader);

        if (authHeader != null) {
            var token = authHeader.replace("Bearer ", "").replace("Bearer:", "").trim();
            System.out.println("Token extraído: " + token);

            var nombreUsuario = tokenService.getSubject(token);
            if (nombreUsuario != null) {
                var usuario = usuarioRepository.findByCorreoElectronico(nombreUsuario);
                var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
                System.out.println("Usuario autenticado: " + nombreUsuario);
            } else {
                System.out.println("No se pudo autenticar el usuario");
            }
        } else {
            System.out.println("No se encontró header de autorización");
        }
        filterChain.doFilter(request, response);
    }
}