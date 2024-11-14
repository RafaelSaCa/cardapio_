package com.rfsaca.cardapio.cardapio.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rfsaca.cardapio.cardapio.dto.request.RegisterUsuarioRequest;
import com.rfsaca.cardapio.cardapio.enums.Role;
import com.rfsaca.cardapio.cardapio.models.Usuario;
import com.rfsaca.cardapio.cardapio.repositories.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class RegisterController {

  private final UsuarioRepository repository;

  @PostMapping
  public ResponseEntity<Void> register (@RequestHeader(value="isAdmin", required=false) boolean isAdmin, 
                                        @RequestBody RegisterUsuarioRequest request ){

      Usuario novoUsuario = Usuario.builder()
              .name(request.getNome())
              .email(request.getEmail())
              .roles(isAdmin ? List.of( Role.ADMIN, Role.USER) : List.of(Role.USER))
                .password( new BCryptPasswordEncoder().encode(request.getSenha()))
                .build();

      repository.save(novoUsuario);

      return ResponseEntity.status(HttpStatus.CREATED).build();
  }
}
  