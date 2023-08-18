package br.com.example.PizzariaSpring.controller;

import br.com.example.PizzariaSpring.entities.EstoqueProdEntity;
import br.com.example.PizzariaSpring.repositories.EstoqueProdRepository;
import br.com.example.PizzariaSpring.services.EstoqueProdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/estoque_prod")
public class EstoqueProdController {
    @Autowired
    private EstoqueProdRepository estoqueProdRepository;

    @Autowired
    private EstoqueProdService estoqueProdService;

    @GetMapping("/estoque_prod")
    public List<EstoqueProdEntity> fetchEstoqueProd (){
        return estoqueProdRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstoqueProdEntity> findById(@PathVariable("id") final Long id){
        final EstoqueProdEntity estoqueProd = this.estoqueProdRepository.findById(id).orElse(null);
        return ResponseEntity.ok(estoqueProd);
    }
    @GetMapping("/lista")
    public ResponseEntity <?> ListaCompleta(){
        return ResponseEntity.ok(this.estoqueProdRepository.findAll());
    }

    @PostMapping
    public ResponseEntity <?> cadastrar(@Validated @RequestBody final EstoqueProdEntity estoqueProd){
        try {
              estoqueProdService.validaEstoqueProd(estoqueProd);
            return ResponseEntity.ok("EstoqueProd cadastrado com sucesso!!!");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") final Long id, @RequestBody final EstoqueProdEntity estoqueProd){
        try {
            estoqueProdService.validaEstoqueProd(estoqueProd);
            final EstoqueProdEntity estoqueProd1 = this.estoqueProdRepository.findById(id).orElse(null);
            if (estoqueProd1 == null || !estoqueProd1.getId().equals(estoqueProd.getId())){
                throw new RuntimeException("Nao foi possivel indentificar o registro informado");
            }
            this.estoqueProdRepository.save(estoqueProd);
            return ResponseEntity.ok("EstoqueProd atualizado com Sucesso!!!");
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
            return ResponseEntity.ok("Registro excluido com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
