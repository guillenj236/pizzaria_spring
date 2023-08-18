package br.com.example.PizzariaSpring.repositories;

import br.com.example.PizzariaSpring.entities.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity,Long> {
}
