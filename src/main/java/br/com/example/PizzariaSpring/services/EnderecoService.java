package br.com.example.PizzariaSpring.services;

import br.com.example.PizzariaSpring.entities.EnderecoEntity;
import br.com.example.PizzariaSpring.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Transactional(rollbackFor = Exception.class)
    public void validaEndereco(EnderecoEntity Endereco) {
        Assert.isTrue(Endereco.getRua().length() <= 50, "Endereco maior que 50 caracteres");
        this.enderecoRepository.save(Endereco);
    }
    public void atualizaEndereco (EnderecoEntity Endereco){
        final EnderecoEntity EnderecoBancoDeDados = this.enderecoRepository.findById(Endereco.getId()).orElse(null);
        Endereco.setRua(EnderecoBancoDeDados.getRua());

        this.enderecoRepository.save(Endereco);
    }
}
