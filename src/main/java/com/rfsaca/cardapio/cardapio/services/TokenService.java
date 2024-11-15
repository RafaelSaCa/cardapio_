package com.rfsaca.cardapio.cardapio.services;

import java.time.Instant;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.rfsaca.cardapio.cardapio.config.JWTUserData;
import com.rfsaca.cardapio.cardapio.enums.Role;
import com.rfsaca.cardapio.cardapio.models.Usuario;

@Service
public class TokenService {

    @Value("${security.secretLoginKey}")
    private String secret;

    public String geraToken(Usuario usuario) {
        Algorithm algorithm = Algorithm.HMAC256(secret);

        return JWT.create()
                .withIssuer("cardapio-api")
                .withClaim("usuarioId", usuario.getId())
                .withSubject(usuario.getEmail())
                .withClaim("roles", usuario.getRoles().stream().map(Enum::name).toList())
                .withExpiresAt(Instant.now().plusSeconds(86400))
                .withIssuedAt(Instant.now())
                .sign(algorithm);
    }

    public Optional<JWTUserData> validaToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            DecodedJWT decode = JWT.require(algorithm)
                    .withIssuer("cardapio-api")
                    .build()
                    .verify(token);

            return Optional.of(JWTUserData.builder()
                    .email(decode.getSubject())
                    .usuarioId(decode.getClaim("usuarioId").asLong())
                    .roles(decode.getClaim("roles").asList(Role.class))
                    .build());

        } catch (JWTVerificationException e) {
            return Optional.empty();
        }
    }
}