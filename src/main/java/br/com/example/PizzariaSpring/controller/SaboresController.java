package br.com.example.PizzariaSpring.controller;

import br.com.example.PizzariaSpring.entities.SaboresEntity;
import br.com.example.PizzariaSpring.repositories.SaboresRepository;
import br.com.example.PizzariaSpring.services.SaboresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/sabores")
public class SaboresController {
    @Autowired
    private SaboresRepository saboresRepository;

    @Autowired
    private SaboresService saboresService;

    @GetMapping("/sabores")
    public List<SaboresEntity> fetchsabores (){
        return saboresRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaboresEntity> findById(@PathVariable("id") final Long id){
        final SaboresEntity sabores = this.saboresRepository.findById(id).orElse(null);
        return ResponseEntity.ok(sabores);
    }
    @GetMapping("/lista")
    public ResponseEntity <?> ListaCompleta(){
        return ResponseEntity.ok(this.saboresRepository.findAll());
    }

    @PostMapping
    public ResponseEntity <?> cadastrar(@Validated @RequestBody final SaboresEntity sabores){
        try {
            saboresService.validasabores(sabores);
            return ResponseEntity.ok("sabores cadastrado com sucesso!!!");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") final Long id, @RequestBody final SaboresEntity sabores){
        try {
               saboresService.validasabores(sabores);
            final SaboresEntity sabores1 = this.saboresRepository.findById(id).orElse(null);
            if (sabores1 == null || !sabores1.getId().equals(sabores.getId())){
                throw new RuntimeException("Nao foi possivel indentificar o registro informado");
            }
            this.saboresRepository.save(sabores);
            return ResponseEntity.ok("sabores atualizado com Sucesso!!!");
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
//            this.saboresService.deletarEstoque(id);
            return ResponseEntity.ok("Registro excluido com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
