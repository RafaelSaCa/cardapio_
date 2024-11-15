package com.rfsaca.cardapio.cardapio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rfsaca.cardapio.cardapio.models.Pedido;
import com.rfsaca.cardapio.cardapio.models.Usuario;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
  
  List<Pedido> findPedidoByUsuario (Usuario usuario);
}
