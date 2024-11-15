package com.rfsaca.cardapio.cardapio.dto.response;

import java.util.ArrayList;
import java.util.List;

import com.rfsaca.cardapio.cardapio.dto.request.ItemPedidoRequest;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PedidoResponse {

    private Long id;

    private String usuario;

    private List<ItemPedidoRequest> itens  = new ArrayList<>();;

    private Double total;
}
