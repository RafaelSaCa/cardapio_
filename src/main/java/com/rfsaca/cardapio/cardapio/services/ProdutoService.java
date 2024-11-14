package com.rfsaca.cardapio.cardapio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rfsaca.cardapio.cardapio.dto.ProdutoDto;
import com.rfsaca.cardapio.cardapio.exceptions.RecursoNotFoundException;
import com.rfsaca.cardapio.cardapio.mappers.ProdutoMapper;
import com.rfsaca.cardapio.cardapio.models.Produto;
import com.rfsaca.cardapio.cardapio.repositories.ProdutoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private ProdutoMapper mapper;

    @Transactional
    public ProdutoDto salvar(Produto produto) {
        Produto produtoSalvo = repository.save(produto);

        return mapper.toDto(produtoSalvo);
    }

    @Transactional
    public ProdutoDto atualizarProduto(Long id, Produto produto) {
        Produto produtoExistente = repository.findById(id)
                .orElseThrow(() -> new RecursoNotFoundException("Produto não encontrado!"));

        produtoExistente.setNome(produto.getNome());
        produtoExistente.setDescricao(produto.getDescricao());
        produtoExistente.setValor(produto.getValor());
        produtoExistente.setQuantidade(produto.getQuantidade());
        produtoExistente.setTipo(produto.getTipo());
        produtoExistente.setImgUrl(produto.getImgUrl());

        Produto produtoSalvo = repository.save(produtoExistente);
        return mapper.toDto(produtoSalvo);
    }

    public List<ProdutoDto> listaTodos() {
        List<Produto> produtos = repository.findAll();

        return mapper.toListDto(produtos);
    }

    public ProdutoDto buscaPorId(Long id) {
        Produto produto = repository.findById(id)
                .orElseThrow(() -> new RecursoNotFoundException("Produto não encontrado!"));

        return mapper.toDto(produto);
    }

    @Transactional
    public void remove(Long id) {
        if (!repository.existsById(id)) {
            throw new RecursoNotFoundException("Produto não encontrado!");
        }
        repository.deleteById(id);
    }

}
