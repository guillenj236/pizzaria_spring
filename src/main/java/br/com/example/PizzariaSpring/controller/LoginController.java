package br.com.example.PizzariaSpring.controller;

import br.com.example.PizzariaSpring.entities.LoginEntity;
import br.com.example.PizzariaSpring.repositories.LoginRepository;
import br.com.example.PizzariaSpring.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(value = "/api/login")
public class LoginController {
    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    public List<LoginEntity> fetchLogin (){
        return loginRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LoginEntity> findById(@PathVariable("id") final Long id){
        final LoginEntity Login = this.loginRepository.findById(id).orElse(null);
        return ResponseEntity.ok(Login);
    }
    @GetMapping("/lista")
    public ResponseEntity <?> ListaCompleta(){
        return ResponseEntity.ok(this.loginRepository.findAll());
    }

    @PostMapping
    public ResponseEntity <?> cadastrar(@Validated @RequestBody final LoginEntity login){
        try {
            loginService.validaLogin(login);
            return ResponseEntity.ok("Login cadastrado com sucesso!!!");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") final Long id, @RequestBody final LoginEntity Login){
        try {
               loginService.validaLogin(Login);
            final LoginEntity Login1 = this.loginRepository.findById(id).orElse(null);
            if (Login1 == null || !Login1.getId().equals(Login.getId())){
                throw new RuntimeException("Nao foi possivel indentificar o registro informado");
            }
            this.loginRepository.save(Login);
            return ResponseEntity.ok("Login atualizado com Sucesso!!!");
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
