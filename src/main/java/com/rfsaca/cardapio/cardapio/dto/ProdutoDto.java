package com.rfsaca.cardapio.cardapio.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDto {

    private Long id;
    
    private String nome;

    private String descricao;

    private Double valor;

    private Integer quantidade;

    private String imgUrl;

    private String tipo;

}
