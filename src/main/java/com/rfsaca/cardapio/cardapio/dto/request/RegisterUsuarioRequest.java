package com.rfsaca.cardapio.cardapio.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUsuarioRequest {

    private String nome;
    private String email;
    private String senha;

}
