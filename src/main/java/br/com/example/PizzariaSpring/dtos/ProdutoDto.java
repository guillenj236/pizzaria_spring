package br.com.example.PizzariaSpring.dtos;

import br.com.example.PizzariaSpring.entities.EstoqueProdEntity;

import lombok.Data;

@Data
public class ProdutoDto {
    private Integer quantidade;

    private EstoqueProdEntity estoqueProd;
}
