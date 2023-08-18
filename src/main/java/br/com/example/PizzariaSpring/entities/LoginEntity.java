package br.com.example.PizzariaSpring.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_login", schema = "public")
public class LoginEntity extends AbstractEntity{
    @Getter
    @Setter
    @Column(name = "login")
    private String login;
    @Getter
    @Setter
    private String senha;
}
