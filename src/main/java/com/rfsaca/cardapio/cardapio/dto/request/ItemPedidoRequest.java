package com.rfsaca.cardapio.cardapio.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.rfsaca.cardapio.cardapio.dto.response.ProdutoResponse;
import com.rfsaca.cardapio.cardapio.models.Pedido;

import lombok.Data;

@Data
public class ItemPedidoRequest {

    private Long id;

    private ProdutoResponse produto;

    private Integer quantidade;

    private Double valor;
    
    @JsonIgnore
    private Pedido pedido;

}
