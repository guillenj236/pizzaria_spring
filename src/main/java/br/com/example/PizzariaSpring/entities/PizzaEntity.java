package br.com.example.PizzariaSpring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_pizza", schema = "public")
public class PizzaEntity extends AbstractEntity{
    @Getter
    @Setter
    @ManyToMany
    @JoinColumn(name = "sabores_id")
    private List<SaboresEntity> sabores;
    @Getter
    @Setter
    @Column(name = "preco_pizza")
    private Float precoPizza;
    @Getter
    @Setter
    @Column(name = "quant_pizza")
    private Integer qtdPizza;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    @Column(name = "tamanho_pizza")
    private Tamanho tamanho;


}
