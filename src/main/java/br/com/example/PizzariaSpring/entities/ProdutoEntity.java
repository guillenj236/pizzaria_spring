package br.com.example.PizzariaSpring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_produto", schema = "public")
public class ProdutoEntity extends AbstractEntity{
    @Getter
    @Setter
    @Column(name = "quantidade_prod")
    private Integer quantidade;

    @Getter
    @Setter
    @ManyToMany
    @JoinColumn(name = "estoque_prod")
    private List<EstoqueProdEntity> estoqueProd;
}
