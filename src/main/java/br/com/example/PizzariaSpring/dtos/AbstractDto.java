package br.com.example.PizzariaSpring.dtos;

import lombok.Data;

@Data
public class AbstractDto {

    private Long id;
    public AbstractDto(){
    }
    public AbstractDto(Long id) {
        this.id = id;
    }
}
