package br.com.example.PizzariaSpring.services;

import br.com.example.PizzariaSpring.entities.UsuarioEntity;
import br.com.example.PizzariaSpring.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional(rollbackFor = Exception.class)
    public void validausuario(UsuarioEntity usuario) {
        Assert.isTrue(usuario.getNome().length() <= 50, "usuario maior que 50 caracteres");
        this.usuarioRepository.save(usuario);
    }
    public void atualizausuario (UsuarioEntity usuario){
        final UsuarioEntity usuarioBancoDeDados = this.usuarioRepository.findById(usuario.getId()).orElse(null);
        usuario.setNome(usuarioBancoDeDados.getNome());

        this.usuarioRepository.save(usuario);
    }
}
