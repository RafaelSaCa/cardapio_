package com.rfsaca.cardapio.cardapio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.rfsaca.cardapio.cardapio.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
  
  UserDetails findByEmail(String email);
}
