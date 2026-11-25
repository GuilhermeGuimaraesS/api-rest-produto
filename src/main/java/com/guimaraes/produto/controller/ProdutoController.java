package com.guimaraes.produto.controller;

import com.guimaraes.produto.dto.ProdutoRequestDTO;
import com.guimaraes.produto.dto.ProdutoResponseDTO;
import com.guimaraes.produto.service.ProdutoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> findAll(){
        return ResponseEntity.ok().body(produtoService.findAll());
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<ProdutoResponseDTO> findByCodigo(@PathVariable String codigo){

        return ResponseEntity.ok().body(produtoService.findByCodigo(codigo));
    }

    @PostMapping
    public ProdutoResponseDTO create(@RequestBody ProdutoRequestDTO produtoRequestDTO){
        return produtoService.create(produtoRequestDTO);
    }
}
