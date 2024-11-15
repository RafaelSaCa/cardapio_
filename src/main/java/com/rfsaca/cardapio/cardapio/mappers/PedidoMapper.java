package com.rfsaca.cardapio.cardapio.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.rfsaca.cardapio.cardapio.dto.request.PedidoRequest;
import com.rfsaca.cardapio.cardapio.dto.response.PedidoResponse;
import com.rfsaca.cardapio.cardapio.models.Pedido;

@Component
public class PedidoMapper {

   private final ModelMapper mapper;

    public PedidoMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public PedidoResponse toResponse (Pedido pedido){
      return mapper.map(pedido, PedidoResponse.class);
    }
   
    public Pedido toEntity (PedidoResponse pedidoResponse){
      return mapper.map(pedidoResponse, Pedido.class );
    }

    public Pedido toEntity (PedidoRequest pedidoRequest){
      return mapper.map(pedidoRequest, Pedido.class);
    }
  

    public List<PedidoResponse> toListResponse (List<Pedido> pedidos){
      return pedidos.stream()
                    .map(pedido -> mapper.map(pedido, PedidoResponse.class))
                    .collect(Collectors.toList());
    }

  

} 
