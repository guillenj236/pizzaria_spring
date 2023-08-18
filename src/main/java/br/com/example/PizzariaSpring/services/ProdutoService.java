package br.com.example.PizzariaSpring.services;

import br.com.example.PizzariaSpring.entities.ProdutoEntity;
import br.com.example.PizzariaSpring.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional(rollbackFor = Exception.class)
    public void validaproduto(ProdutoEntity produto) {
        this.produtoRepository.save(produto);
    }
    public void atualizaproduto (ProdutoEntity produto){
        final ProdutoEntity produtoBancoDeDados = this.produtoRepository.findById(produto.getId()).orElse(null);

        this.produtoRepository.save(produto);
    }
}
