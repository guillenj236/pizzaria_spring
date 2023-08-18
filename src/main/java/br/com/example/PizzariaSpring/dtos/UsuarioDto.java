package br.com.example.PizzariaSpring.dtos;

import br.com.example.PizzariaSpring.entities.EnderecoEntity;
import br.com.example.PizzariaSpring.entities.LoginEntity;

import lombok.Data;

@Data
public class UsuarioDto {
    private String nome;
    private String cpf;
    private LoginEntity login;
    private EnderecoEntity endereco;
}
