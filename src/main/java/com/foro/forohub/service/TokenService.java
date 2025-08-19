package com.foro.forohub.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import com.foro.forohub.domain.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;



    public String generarToken(Usuario usuario) {
        try {
            System.out.println("Generando token con secreto: " + secret);
            String token = Jwts.builder()
                    .setSubject(usuario.getCorreoElectronico())
                    .setIssuer("forohub")
                    .setIssuedAt(new Date())
                    .setExpiration(generarFechaExpiracion())
                    .signWith(generarClave(), SignatureAlgorithm.HS256)
                    .compact();
            System.out.println("Token generado: " + token);
            return token;
        } catch (Exception e) {
            System.out.println("Error al generar token: " + e.getMessage());
            throw new RuntimeException("Error al generar el token JWT", e);
        }
    }

    private SecretKey generarClave() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        System.out.println("Longitud de la clave: " + keyBytes.length + " bytes");
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String getSubject(String token) {
        try {
            System.out.println("Validando token: " + token);
            System.out.println("Usando secreto: " + secret);

            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(generarClave())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            System.out.println("Token válido para: " + claims.getSubject());
            return claims.getSubject();
        } catch (Exception e) {
            System.out.println("Error al validar token: " + e.getMessage());
            throw new RuntimeException("Token JWT inválido o expirado");
        }
    }

    private Date generarFechaExpiracion() {
        return Date.from(Instant.now().plus(2, ChronoUnit.HOURS));
    }
}