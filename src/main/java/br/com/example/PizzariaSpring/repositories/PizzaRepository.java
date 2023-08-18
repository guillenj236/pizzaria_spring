package br.com.example.PizzariaSpring.repositories;

import br.com.example.PizzariaSpring.entities.PizzaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<PizzaEntity,Long> {
}
