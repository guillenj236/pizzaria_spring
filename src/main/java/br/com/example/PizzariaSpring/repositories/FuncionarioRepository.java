package br.com.example.PizzariaSpring.repositories;

import br.com.example.PizzariaSpring.entities.FuncionarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<FuncionarioEntity,Long> {
}
