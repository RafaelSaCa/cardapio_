package com.rfsaca.cardapio.cardapio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rfsaca.cardapio.cardapio.dto.request.PedidoRequest;
import com.rfsaca.cardapio.cardapio.dto.response.PedidoResponse;
import com.rfsaca.cardapio.cardapio.services.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PedidoResponse> criarPedido(@RequestBody PedidoRequest pedidoRequest) {
        PedidoResponse pedidoCriado = service.salvaPedido(pedidoRequest.getItens());
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoCriado);
    }
    
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PedidoResponse>> listaPedidosUsuario (){
        List<PedidoResponse> pedidos = service.findPedidosByUsuario();
        return ResponseEntity.ok(pedidos);
    }
}
