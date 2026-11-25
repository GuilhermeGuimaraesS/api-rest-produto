package com.guimaraes.produto.model.entities;

import com.guimaraes.produto.enums.CategoriaProduto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, updatable = false, length = 8)
    private String codigo;

    @NotBlank
    @Size(max = 100)
    @Column(unique = true)
    private String nome;

    @NotNull
    private Double preco;

    @Column(name = "quantidade_em_estoque")
    private Integer quantidadeEstoque;

    @Enumerated(EnumType.STRING)
    @Column(name = "categoria_produto")
    private CategoriaProduto categoriaProduto;

    @CreationTimestamp
    @Column(name = "registrado_em", updatable = false)
    private LocalDateTime registradoEm;

}
