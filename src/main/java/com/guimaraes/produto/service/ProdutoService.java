package com.guimaraes.produto.service;

import com.guimaraes.produto.dto.*;
import com.guimaraes.produto.enums.CategoriaProduto;
import com.guimaraes.produto.infra.EntityRegisteredException;
import com.guimaraes.produto.model.entities.Produto;
import com.guimaraes.produto.model.repositories.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    private static final SecureRandom RANDOM = new SecureRandom();

    @Autowired
    private ProdutoRepository produtoRepository;


    @Transactional(readOnly = true)
    public List<ProdutoResponseDTO> findAll(){
        return produtoRepository.findAll().stream().map(this::toResponseDTO).toList();
    }

    @Transactional(readOnly = true)
    public ProdutoResponseDTO findByCodigo(String codigo){
        Optional<Produto> produtoOptional = produtoRepository.findByCodigo(codigo);

        if (produtoOptional.isEmpty()){
            throw new EntityNotFoundException("Produto não encontrado: " + codigo);
        }

        return toResponseDTO(produtoOptional.get());
    }

    @Transactional
    public ProdutoResponseDTO create(@Valid ProdutoRequestDTO produtoRequestDTO){

        if (produtoRepository.existsByNome(produtoRequestDTO.nome())){
            throw new EntityRegisteredException("Produto já está cadastrado: " + produtoRequestDTO.nome());
        }

        Produto produto = toEntity(produtoRequestDTO);
        produto.setCodigo(gerarCodigoUnico(produtoRequestDTO.categoriaProduto()));
        Produto produtoSalvo = produtoRepository.save(produto);
        return toResponseDTO(produtoRepository.save(produtoSalvo));
    }

    private String gerarCodigoUnico(CategoriaProduto categoria) {
        String prefixo = categoria.name().substring(0, 3);
        String codigo;

        do {
            int numero = RANDOM.nextInt(10000);
            codigo = String.format("%s-%04d", prefixo, numero);
        } while (produtoRepository.existsByCodigo(codigo));

        return codigo;
    }

    public Produto toEntity(ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = new Produto();
        produto.setNome(produtoRequestDTO.nome());
        produto.setPreco(produtoRequestDTO.preco());
        produto.setQuantidadeEstoque(produtoRequestDTO.quantidadeEstoque());
        produto.setCategoriaProduto(produtoRequestDTO.categoriaProduto());
        return produto;
    }

    public ProdutoResponseDTO toResponseDTO(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getCodigo(),
                produto.getNome(),
                produto.getPreco(),
                produto.getQuantidadeEstoque(),
                produto.getCategoriaProduto()
        );
    }

}
