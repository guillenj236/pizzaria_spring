package br.com.example.PizzariaSpring.repositories;

import br.com.example.PizzariaSpring.entities.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoEntity,Long> {
}
