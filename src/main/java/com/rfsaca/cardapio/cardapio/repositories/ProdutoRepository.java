package com.rfsaca.cardapio.cardapio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rfsaca.cardapio.cardapio.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
