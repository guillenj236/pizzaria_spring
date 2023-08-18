package br.com.example.PizzariaSpring.controller;

import br.com.example.PizzariaSpring.entities.PizzaEntity;
import br.com.example.PizzariaSpring.repositories.PizzaRepository;
import br.com.example.PizzariaSpring.services.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/pizza")
public class PizzaController {
    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private PizzaService pizzaService;

    @GetMapping("/pizza")
    public List<PizzaEntity> fetchpizza (){
        return pizzaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PizzaEntity> findById(@PathVariable("id") final Long id){
        final PizzaEntity pizza = this.pizzaRepository.findById(id).orElse(null);
        return ResponseEntity.ok(pizza);
    }
    @GetMapping("/lista")
    public ResponseEntity <?> ListaCompleta(){
        return ResponseEntity.ok(this.pizzaRepository.findAll());
    }

    @PostMapping
    public ResponseEntity <?> cadastrar(@Validated @RequestBody final PizzaEntity pizza){
        try {
           pizzaService.validapizza(pizza);
            return ResponseEntity.ok("pizza cadastrado com sucesso!!!");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") final Long id, @RequestBody final PizzaEntity pizza){
        try {
               pizzaService.validapizza(pizza);
            final PizzaEntity pizza1 = this.pizzaRepository.findById(id).orElse(null);
            if (pizza1 == null || !pizza1.getId().equals(pizza.getId())){
                throw new RuntimeException("Nao foi possivel indentificar o registro informado");
            }
            this.pizzaRepository.save(pizza);
            return ResponseEntity.ok("pizza atualizado com Sucesso!!!");
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
//            this.pizzaService.deletarEstoque(id);
            return ResponseEntity.ok("Registro excluido com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
