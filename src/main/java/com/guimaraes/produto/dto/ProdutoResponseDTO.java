package com.guimaraes.produto.dto;

import com.guimaraes.produto.enums.CategoriaProduto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record ProdutoResponseDTO(
        String codigo,
        String nome,
        Double preco,
        Integer quantidadeEstoque,
        CategoriaProduto categoriaProduto
){

}
