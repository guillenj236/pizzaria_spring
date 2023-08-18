package br.com.example.PizzariaSpring.services;

import br.com.example.PizzariaSpring.entities.LoginEntity;
import br.com.example.PizzariaSpring.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

@Service
public class LoginService {
    @Autowired
    private LoginRepository loginRepository;

    @Transactional(rollbackFor = Exception.class)
    public void validaLogin(LoginEntity login) {
        Assert.isTrue(login.getLogin().length() <= 50, "login maior que 50 caracteres");
        this.loginRepository.save(login);
    }
    public void atualizalogin (LoginEntity login){
        final LoginEntity loginBancoDeDados = this.loginRepository.findById(login.getId()).orElse(null);
        login.setLogin(loginBancoDeDados.getLogin());

        this.loginRepository.save(login);
    }
}
