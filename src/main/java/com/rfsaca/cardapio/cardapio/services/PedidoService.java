package com.rfsaca.cardapio.cardapio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rfsaca.cardapio.cardapio.config.SecurityContextData;
import com.rfsaca.cardapio.cardapio.dto.request.ItemPedidoRequest;
import com.rfsaca.cardapio.cardapio.dto.request.PedidoRequest;
import com.rfsaca.cardapio.cardapio.dto.response.PedidoResponse;
import com.rfsaca.cardapio.cardapio.dto.response.ProdutoResponse;
import com.rfsaca.cardapio.cardapio.exceptions.RecursoNotFoundException;
import com.rfsaca.cardapio.cardapio.mappers.PedidoMapper;
import com.rfsaca.cardapio.cardapio.mappers.ProdutoMapper;
import com.rfsaca.cardapio.cardapio.models.Pedido;
import com.rfsaca.cardapio.cardapio.models.Produto;
import com.rfsaca.cardapio.cardapio.models.Usuario;
import com.rfsaca.cardapio.cardapio.repositories.PedidoRepository;
import com.rfsaca.cardapio.cardapio.repositories.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRespository;

    @Autowired
    private PedidoMapper mapper;

    @Autowired
    ProdutoMapper produtoMapper;

    @Autowired
    PedidoMapper pedidoMapper;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public PedidoResponse salvaPedido(List<ItemPedidoRequest> itens) {

        PedidoRequest pedidoRequest = new PedidoRequest();
        pedidoRequest.setUsuario(Usuario.builder().id(SecurityContextData.getUserData().getUsuarioId()).build());

        for (ItemPedidoRequest item : itens) {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new RecursoNotFoundException("Produto não encontrado!"));

            produto.setQuantidade(produto.getQuantidade() - item.getQuantidade());
            produtoRepository.save(produto);

            ProdutoResponse produtoResponse = produtoMapper.toProdutoResponse(produto);

            item.setProduto(produtoResponse);
            item.setValor(produtoResponse.getValor());

        }

        pedidoRequest.setItens(itens);
        pedidoRequest.calculaValorTotal();

        Pedido pedido = pedidoMapper.toEntity(pedidoRequest);

        Pedido pedidoSalvo = pedidoRespository.save(pedido);

        return mapper.toResponse(pedidoSalvo);

    }

    public List<PedidoResponse> findPedidosByUsuario() {

        Usuario usuario = Usuario.builder().id(SecurityContextData.getUserData().getUsuarioId()).build();
        List<Pedido> pedidos = pedidoRespository.findPedidoByUsuario(usuario);

        return mapper.toListResponse(pedidos);
    }

}
