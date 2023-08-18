package br.com.example.PizzariaSpring.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@Entity
public class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    public AbstractEntity(){
    }
    public AbstractEntity(Long id) {
        this.id = id;
    }
}
