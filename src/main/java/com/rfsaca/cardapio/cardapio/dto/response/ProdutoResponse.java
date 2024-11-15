package com.rfsaca.cardapio.cardapio.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoResponse {

    private Long id;
    
    private String nome;

    private String descricao;

    private Double valor;

    private String imgUrl;



}
