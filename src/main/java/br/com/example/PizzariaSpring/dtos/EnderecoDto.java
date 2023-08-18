package br.com.example.PizzariaSpring.dtos;


import lombok.Data;

@Data
public class EnderecoDto {

    private String rua;

    private String bairro;

    private String numeroCasa;

    private String CEP;
}
