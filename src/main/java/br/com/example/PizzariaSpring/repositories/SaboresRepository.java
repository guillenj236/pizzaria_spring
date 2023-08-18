package br.com.example.PizzariaSpring.repositories;

import br.com.example.PizzariaSpring.entities.SaboresEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaboresRepository extends JpaRepository<SaboresEntity,Long> {
}
