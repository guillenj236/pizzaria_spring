package br.com.example.PizzariaSpring.services;

import br.com.example.PizzariaSpring.entities.EstoqueProdEntity;
import br.com.example.PizzariaSpring.repositories.EstoqueProdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class EstoqueProdService {
    @Autowired
    private EstoqueProdRepository estoqueProdRepository;

    @Transactional(rollbackFor = Exception.class)
    public void validaEstoqueProd(EstoqueProdEntity estoqueProd) {
        Assert.isTrue(estoqueProd.getNomeProduto().length() <= 50, "estoqueProd maior que 50 caracteres");
        this.estoqueProdRepository.save(estoqueProd);
    }
    public void atualizaEstoqueProd (EstoqueProdEntity estoqueProd){
        final EstoqueProdEntity estoqueProdBancoDeDados = this.estoqueProdRepository.findById(estoqueProd.getId()).orElse(null);
        estoqueProd.setNomeProduto(estoqueProdBancoDeDados.getNomeProduto());

        this.estoqueProdRepository.save(estoqueProd);
    }
}
