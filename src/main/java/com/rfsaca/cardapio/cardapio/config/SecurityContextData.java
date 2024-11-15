package com.rfsaca.cardapio.cardapio.config;

import org.springframework.security.core.context.SecurityContextHolder;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SecurityContextData {

  public static JWTUserData getUserData(){
    return (JWTUserData) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }

}
