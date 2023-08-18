package br.com.example.produtoriaSpring.controller;

import br.com.example.PizzariaSpring.entities.ProdutoEntity;
import br.com.example.PizzariaSpring.repositories.ProdutoRepository;
import br.com.example.PizzariaSpring.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/produto")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/produto")
    public List<ProdutoEntity> fetchproduto (){
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoEntity> findById(@PathVariable("id") final Long id){
        final ProdutoEntity produto = this.produtoRepository.findById(id).orElse(null);
        return ResponseEntity.ok(produto);
    }
    @GetMapping("/lista")
    public ResponseEntity <?> ListaCompleta(){
        return ResponseEntity.ok(this.produtoRepository.findAll());
    }

    @PostMapping
    public ResponseEntity <?> cadastrar(@Validated @RequestBody final ProdutoEntity produto){
        try {
            produtoService.validaproduto(produto);
            return ResponseEntity.ok("produto cadastrado com sucesso!!!");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") final Long id, @RequestBody final ProdutoEntity produto){
        try {
            produtoService.validaproduto(produto);
            final ProdutoEntity produto1 = this.produtoRepository.findById(id).orElse(null);
            if (produto1 == null || !produto1.getId().equals(produto.getId())){
                throw new RuntimeException("Nao foi possivel indentificar o registro informado");
            }
            this.produtoRepository.save(produto);
            return ResponseEntity.ok("produto atualizado com Sucesso!!!");
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
//            this.produtoService.deletarEstoque(id);
            return ResponseEntity.ok("Registro excluido com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
