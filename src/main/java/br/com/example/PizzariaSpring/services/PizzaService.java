package br.com.example.PizzariaSpring.services;

import br.com.example.PizzariaSpring.entities.PizzaEntity;
import br.com.example.PizzariaSpring.repositories.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class PizzaService {
    @Autowired
    private PizzaRepository pizzaRepository;

    @Transactional(rollbackFor = Exception.class)
    public void validapizza(PizzaEntity pizza) {
        this.pizzaRepository.save(pizza);
    }
    public void atualizapizza (PizzaEntity pizza){
        final PizzaEntity pizzaBancoDeDados = this.pizzaRepository.findById(pizza.getId()).orElse(null);

        this.pizzaRepository.save(pizza);
    }
}
