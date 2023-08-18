package br.com.example.PizzariaSpring.services;

import br.com.example.PizzariaSpring.entities.SaboresEntity;
import br.com.example.PizzariaSpring.repositories.SaboresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class SaboresService {
    @Autowired
    private SaboresRepository saboresRepository;

    @Transactional(rollbackFor = Exception.class)
    public void validasabores(SaboresEntity sabores) {
        Assert.isTrue(sabores.getNomeSabor().length() <= 50, "sabores maior que 50 caracteres");
        this.saboresRepository.save(sabores);
    }
    public void atualizasabores (SaboresEntity sabores){
        final SaboresEntity saboresBancoDeDados = this.saboresRepository.findById(sabores.getId()).orElse(null);
        sabores.setNomeSabor(saboresBancoDeDados.getNomeSabor());

        this.saboresRepository.save(sabores);
    }
}
