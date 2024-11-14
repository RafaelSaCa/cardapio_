package com.rfsaca.cardapio.cardapio.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.rfsaca.cardapio.cardapio.dto.ProdutoDto;
import com.rfsaca.cardapio.cardapio.models.Produto;

@Component
public class ProdutoMapper {

  private final ModelMapper mapper; 

  public ProdutoMapper(ModelMapper mapper) {
    this.mapper = mapper;
  }


  public ProdutoDto toDto (Produto produto){
    return mapper.map(produto, ProdutoDto.class);
  }

  public Produto toEntity (ProdutoDto produtoDto){
    return mapper.map(produtoDto, Produto.class);
  }

  public List<ProdutoDto> toListDto (List<Produto> produtos){
    return produtos.stream()
                   .map(produto -> mapper.map(produto, ProdutoDto.class))
                   .collect(Collectors.toList());
  }
}
