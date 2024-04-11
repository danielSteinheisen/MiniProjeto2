package br.com.fullStack.education.M1S12.services;

import br.com.fullStack.education.M1S12.entities.AlunoEntity;
import br.com.fullStack.education.M1S12.exceptions.NotFoundException;
import br.com.fullStack.education.M1S12.repositories.AlunoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AlunoServiceImpl implements AlunoService {

    private final AlunoRepository repository;

    @Override
    public List<AlunoEntity> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public AlunoEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado com id: " + id));
    }

    @Override
    public AlunoEntity criar(AlunoEntity entity) {
        entity.setId(null);
        return repository.save(entity);
    }

    @Override
    public AlunoEntity alterar(Long id, AlunoEntity entity) {
        buscarPorId(id);
        entity.setId(id);
        return repository.save(entity);
    }

    @Override
    public void excluir(Long id) {
        AlunoEntity entity = buscarPorId(id);
        repository.delete(entity);
    }
}
