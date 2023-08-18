package br.com.example.PizzariaSpring.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "tb_funcinario", schema = "public")
public class FuncionarioEntity extends AbstractEntity{
    @Getter
    @Setter
    @Column(name = "nome_funcionario")
    private String nomeFuncionario;
}
