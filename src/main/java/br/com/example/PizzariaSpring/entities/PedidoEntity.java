package br.com.example.PizzariaSpring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_pedido", schema = "public")
public class PedidoEntity extends AbstractEntity{
    @Getter
    @Setter
    private String observacao;
    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;
    @Getter
    @Setter
    @Column(name = "pedido_preco")
    private Float pedidoPreco;
    @Getter
    @Setter
    @Column(name = "delivery")
    private Boolean delivery;
    @Getter
    @Setter
    @OneToMany
    @JoinColumn(name = "pizza_id")
    private List<PizzaEntity> pizza;
    @Getter
    @Setter
    @Column(name = "cancelado")
    private Boolean cancelado;
    @Getter
    @Setter
    @Column(name = "pagamento_cartao")
    private Boolean pagamentoCartao;

    @Getter
    @Setter
    @OneToMany
    @JoinColumn(name = "produto_id")
    private List<ProdutoEntity> produto;

}
