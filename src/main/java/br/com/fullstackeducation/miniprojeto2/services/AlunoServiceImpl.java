package br.com.fullstackeducation.miniprojeto2.services;

import br.com.fullstackeducation.miniprojeto2.entities.AlunoEntity;
import br.com.fullstackeducation.miniprojeto2.exceptions.NotFoundException;
import br.com.fullstackeducation.miniprojeto2.repositories.AlunoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class AlunoServiceImpl implements AlunoService {

    private final AlunoRepository repository;

    @Override
    public List<AlunoEntity> buscarTodos() {
        log.info("Buscando todos os alunos");
        return repository.findAll();
    }

    @Override
    public AlunoEntity buscarPorId(Long id) {
        log.info("Buscando o aluno pelo id: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "Aluno não encontrado com id: " + id
                ));
    }

    @Override
    public AlunoEntity criar(AlunoEntity entity) {
        log.info("Criando um novo aluno: {}", entity);
        entity.setId(null);
        return repository.save(entity);
    }

    @Override
    public AlunoEntity alterar(Long id, AlunoEntity entity) {
        log.info("Alterando o aluno com id: {} para: {}", id, entity);
        buscarPorId(id);
        entity.setId(id);
        return repository.save(entity);
    }

    @Override
    public void excluir(Long id) {
        log.info("Excluindo o aluno com id: {}", id);
        AlunoEntity entity = buscarPorId(id);
        repository.delete(entity);
    }
}
