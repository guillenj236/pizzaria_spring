package br.com.example.PizzariaSpring.dtos;

import br.com.example.PizzariaSpring.entities.SaboresEntity;
import br.com.example.PizzariaSpring.entities.Tamanho;
import lombok.Data;

@Data
public class PizzaDto {
    private SaboresEntity sabores;

    private Float precoPizza;

    private Integer qtdPizza;

    private Tamanho tamanho;

}
