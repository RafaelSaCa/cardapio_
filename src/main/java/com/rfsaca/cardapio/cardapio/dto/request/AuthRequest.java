package com.rfsaca.cardapio.cardapio.dto.request;

import lombok.Data;

@Data
public class AuthRequest {
  
  public String username;
  public String password;
}