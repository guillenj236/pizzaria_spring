package br.com.example.PizzariaSpring.controller;

import br.com.example.PizzariaSpring.entities.PedidoEntity;
import br.com.example.PizzariaSpring.repositories.PedidoRepository;
import br.com.example.PizzariaSpring.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "/api/pedido")
public class PedidoController {
    @Autowired
    private PedidoRepository pedidoRepository;

   @Autowired
    private PedidoService pedidoService;

    @GetMapping("/pedido")
    public List<PedidoEntity> fetchPedido (){
        return pedidoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoEntity> findById(@PathVariable("id") final Long id){
        final PedidoEntity pedido = this.pedidoRepository.findById(id).orElse(null);
        return ResponseEntity.ok(pedido);
    }
    @GetMapping("/lista")
    public ResponseEntity <?> ListaCompleta(){
        return ResponseEntity.ok(this.pedidoRepository.findAll());
    }

    @PostMapping
    public ResponseEntity <?> cadastrar(@Validated @RequestBody final PedidoEntity pedido){
        try {
//            PedidoService.validaPedido(pedido);
            return ResponseEntity.ok("Pedido cadastrado com sucesso!!!");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") final Long id, @RequestBody final PedidoEntity pedido){
        try {
                pedidoService.validapedido(pedido);
            final PedidoEntity Pedido1 = this.pedidoRepository.findById(id).orElse(null);
            if (Pedido1 == null || !Pedido1.getId().equals(pedido.getId())){
                throw new RuntimeException("Nao foi possivel indentificar o registro informado");
            }
            this.pedidoRepository.save(pedido);
            return ResponseEntity.ok("Pedido atualizado com Sucesso!!!");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") final Long id
    ) {
        try {
//            this.PedidoService.deletarEstoque(id);
            return ResponseEntity.ok("Registro excluido com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
