package com.rfsaca.cardapio.cardapio.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class ItemPedido {

  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name= "pedido_id")
  private Pedido pedido;


  @ManyToOne
  @JoinColumn(name= "produto_id")
  private Produto produto;

  private Integer quantidade;

  private Double valor;

}
