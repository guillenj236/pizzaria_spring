package br.com.example.PizzariaSpring.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_estoque_prods", schema = "public")
public class EstoqueProdEntity extends AbstractEntity{
    @Getter
    @Setter
    private Float precoProdutos;
    @Getter
    @Setter
    private String nomeProduto;
}
