package br.com.example.PizzariaSpring.repositories;

import br.com.example.PizzariaSpring.entities.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity,Long> {
}
