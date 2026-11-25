package com.guimaraes.produto.enums;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CategoriaProduto {

    ALIMENTO("alimento"),
    ELETRONICO("eletronico"),
    LIMPEZA("limpeza"),
    VESTUARIO("vestuario"),
    OUTROS("outros");

    private final String categoria;

    CategoriaProduto(String categoria) {
        this.categoria = categoria;
    }

    @JsonValue
    public String getCategoria() {
        return categoria;
    }

    @JsonCreator
    public static CategoriaProduto stringParaCategoria(String categoria){
        for (CategoriaProduto categoriaProduto : CategoriaProduto.values()){
            if (categoriaProduto.getCategoria().equalsIgnoreCase(categoria)){
                return categoriaProduto;
            }
        }

        throw new IllegalArgumentException("Categoria inválida: " + categoria);
    }
}