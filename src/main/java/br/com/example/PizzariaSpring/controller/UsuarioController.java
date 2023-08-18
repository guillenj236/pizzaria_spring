package br.com.example.PizzariaSpring.controller;

import br.com.example.PizzariaSpring.entities.UsuarioEntity;
import br.com.example.PizzariaSpring.repositories.UsuarioRepository;
import br.com.example.PizzariaSpring.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuario")
    public List<UsuarioEntity> fetchusuario (){
        return usuarioRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioEntity> findById(@PathVariable("id") final Long id){
        final UsuarioEntity usuario = this.usuarioRepository.findById(id).orElse(null);
        return ResponseEntity.ok(usuario);
    }
    @GetMapping("/lista")
    public ResponseEntity <?> ListaCompleta(){
        return ResponseEntity.ok(this.usuarioRepository.findAll());
    }

    @PostMapping
    public ResponseEntity <?> cadastrar(@Validated @RequestBody final UsuarioEntity usuario){
        try {
           usuarioService.validausuario(usuario);
            return ResponseEntity.ok("usuario cadastrado com sucesso!!!");
        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editar(@PathVariable("id") final Long id, @RequestBody final UsuarioEntity usuario){
        try {
           usuarioService.validausuario(usuario);
            final UsuarioEntity usuario1 = this.usuarioRepository.findById(id).orElse(null);
            if (usuario1 == null || !usuario1.getId().equals(usuario.getId())){
                throw new RuntimeException("Nao foi possivel indentificar o registro informado");
            }
            this.usuarioRepository.save(usuario);
            return ResponseEntity.ok("usuario atualizado com Sucesso!!!");
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
//            this.usuarioService.deletarEstoque(id);
            return ResponseEntity.ok("Registro excluido com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }
}
