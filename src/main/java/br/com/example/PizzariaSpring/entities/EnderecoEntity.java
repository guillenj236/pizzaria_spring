package br.com.example.PizzariaSpring.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_endereco", schema = "public")
public class EnderecoEntity extends AbstractEntity{
    @Getter
    @Setter
    @Column(name = "rua")
    private String rua;
    @Getter
    @Setter
    @Column(name = "bairro")
    private String bairro;
    @Getter
    @Setter
    @Column(name = "numero_casa")
    private String numeroCasa;
    @Getter
    @Setter
    @Column(name = "CEP")
    private String CEP;

}
