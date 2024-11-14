package com.rfsaca.cardapio.cardapio.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name= "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy= "pedido", cascade= CascadeType.ALL)
    private List<ItemPedido> itens;

    private Double total;

    public void calculaTotal() {
        this.total = itens.stream()
                .mapToDouble(item -> item.getValor() * item.getQuantidade())
                .sum();
    }

}
