package com.rfsaca.cardapio.cardapio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rfsaca.cardapio.cardapio.models.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
