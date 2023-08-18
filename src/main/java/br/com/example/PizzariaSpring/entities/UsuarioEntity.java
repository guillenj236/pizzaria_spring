package br.com.example.PizzariaSpring.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "tb_usuario", schema = "public")
public class UsuarioEntity extends AbstractEntity{
    @Getter
    @Setter
    @Column(name = "nome")
    private String nome;
    @Getter
    @Setter
    @Column(name = "cpf")
    private String cpf;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "login_id")
    private LoginEntity login;
    @Getter
    @Setter
    @OneToMany
    @JoinColumn(name = "endereco_id")
    private List<EnderecoEntity> endereco;


}
