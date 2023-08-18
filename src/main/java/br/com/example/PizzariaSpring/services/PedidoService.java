package br.com.example.PizzariaSpring.services;

import br.com.example.PizzariaSpring.entities.PedidoEntity;
import br.com.example.PizzariaSpring.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class PedidoService {
    @Autowired
    private PedidoRepository pedidoRepository;

    @Transactional(rollbackFor = Exception.class)
    public void validapedido(PedidoEntity pedido) {
        this.pedidoRepository.save(pedido);
    }
    public void atualizapedido (PedidoEntity pedido){
        final PedidoEntity pedidoBancoDeDados = this.pedidoRepository.findById(pedido.getId()).orElse(null);

        this.pedidoRepository.save(pedido);
    }
}
