package br.com.example.PizzariaSpring.dtos;

import br.com.example.PizzariaSpring.entities.PizzaEntity;
import br.com.example.PizzariaSpring.entities.ProdutoEntity;
import br.com.example.PizzariaSpring.entities.UsuarioEntity;

import lombok.Data;

@Data
public class PedidoDto {
    private String observacao;

    private UsuarioEntity usuario;

    private Float pedidoPreco;

    private Boolean delivery;

    private PizzaEntity pizza;

    private Boolean cancelado;

    private Boolean pagamentoCartao;

    private ProdutoEntity produto;
}
