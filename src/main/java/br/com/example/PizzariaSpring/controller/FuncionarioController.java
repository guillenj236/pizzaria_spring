package br.com.example.PizzariaSpring.controller;

import br.com.example.PizzariaSpring.dtos.FuncionarioDto;
import br.com.example.PizzariaSpring.entities.FuncionarioEntity;
import br.com.example.PizzariaSpring.repositories.FuncionarioRepository;
import br.com.example.PizzariaSpring.services.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "/api/funcionario")
public class FuncionarioController {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("/{id}")
    public ResponseEntity<?> findByIdPath(@PathVariable("id") final Long id) {
        final FuncionarioEntity funcionario = this.funcionarioRepository.findById(id).orElse(null);
        return funcionario == null
                ? ResponseEntity.badRequest().body("Nenhum funcionário encontrado para o ID = " + id + ".")
                : ResponseEntity.ok(funcionario);
    }

    @GetMapping("/lista")
    public ResponseEntity<?> listaCompleta() {
        return ResponseEntity.ok(this.funcionarioRepository.findAll());
    }


    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody final FuncionarioDto funcionarioDTO) {
        try {
            this.funcionarioService.validaFuncionario(funcionarioDTO);
            return ResponseEntity.ok("Funcionario cadastrado com sucesso.");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") final Long id, @RequestBody final FuncionarioEntity funcionario) {
        try {
            this.funcionarioService.editaFuncionario(funcionario);
            return ResponseEntity.ok("Funcionario atualizado com sucesso. ");
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getCause().getCause().getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(
            @PathVariable("id") final Long id
    ) {
        try {
            this.funcionarioService.deletarFuncionario(id);
            return ResponseEntity.ok("Funcionário excluido com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
