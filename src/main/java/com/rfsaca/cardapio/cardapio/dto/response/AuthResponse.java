package com.rfsaca.cardapio.cardapio.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {

    @JsonProperty("acess_token")
    private String acessToken;
    
    private String nome;
}