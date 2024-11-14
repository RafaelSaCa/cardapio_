package com.rfsaca.cardapio.cardapio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rfsaca.cardapio.cardapio.dto.ProdutoDto;
import com.rfsaca.cardapio.cardapio.exceptions.RecursoNotFoundException;
import com.rfsaca.cardapio.cardapio.models.Produto;
import com.rfsaca.cardapio.cardapio.services.ProdutoService;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping
    public ResponseEntity<ProdutoDto> cadastrar(@RequestBody Produto produto) {
        ProdutoDto produtoSalvo = service.salvar(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDto> atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        try {
            ProdutoDto produtoAtualizado = service.atualizarProduto(id, produto);
            return ResponseEntity.ok(produtoAtualizado);

        } catch (RecursoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @GetMapping
    public ResponseEntity<List<ProdutoDto>> listaProdutos() {
        List<ProdutoDto> produtos = service.listaTodos();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoDto> buscaProduto(@PathVariable Long id) {
        try {
            ProdutoDto produto = service.buscaPorId(id);
            return ResponseEntity.ok(produto);

        } catch (RecursoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            service.remove(id);
            return ResponseEntity.noContent().build();

        } catch (RecursoNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    
}
