package com.rfsaca.cardapio.cardapio.dto.request;

import java.util.ArrayList;
import java.util.List;

import com.rfsaca.cardapio.cardapio.models.Usuario;

import lombok.Data;

@Data
public class PedidoRequest {

    private Usuario usuario;

    private List<ItemPedidoRequest> itens = new ArrayList<>();

    private Double valorTotal;

    public void calculaValorTotal(){
      valorTotal = itens.stream()
                  .mapToDouble(item -> item.getValor() * item.getQuantidade()).sum();
  }
}
