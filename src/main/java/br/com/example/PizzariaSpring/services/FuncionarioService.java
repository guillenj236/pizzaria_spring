package br.com.example.PizzariaSpring.services;

import br.com.example.PizzariaSpring.dtos.FuncionarioDto;
import br.com.example.PizzariaSpring.entities.FuncionarioEntity;
import br.com.example.PizzariaSpring.repositories.FuncionarioRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FuncionarioService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Transactional(rollbackFor = Exception.class)
    public void validaFuncionario (final FuncionarioDto funcionarioDTO){
        var funcionario = new FuncionarioEntity();
        BeanUtils.copyProperties(funcionarioDTO,funcionario);

        this.funcionarioRepository.save(funcionario);
    }

    @Transactional(rollbackFor = Exception.class)
    public void editaFuncionario (final FuncionarioEntity funcionario){
        final FuncionarioEntity funcionarioEntity = this.funcionarioRepository.findById(funcionario.getId()).orElse(null);

        this.funcionarioRepository.save(funcionario);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deletarFuncionario (final Long id){

        final FuncionarioEntity funcionario1 = this.funcionarioRepository.findById(id).orElse(null);

        if (funcionario1 == null || !funcionario1.getId().equals(id)){
            throw new RuntimeException("Não foi possivel encontrar o funcionário.");
        }

        this.funcionarioRepository.delete(funcionario1);
    }
}
