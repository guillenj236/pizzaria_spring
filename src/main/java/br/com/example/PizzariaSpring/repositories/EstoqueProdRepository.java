package br.com.example.PizzariaSpring.repositories;

import br.com.example.PizzariaSpring.entities.EstoqueProdEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.StringJoiner;

@Repository
public interface EstoqueProdRepository extends JpaRepository<EstoqueProdEntity,Long> {

    List<EstoqueProdEntity> findByid(Long id);
}
