package br.com.example.PizzariaSpring.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class LoginDto {
    private String login;

    private String senha;
}
