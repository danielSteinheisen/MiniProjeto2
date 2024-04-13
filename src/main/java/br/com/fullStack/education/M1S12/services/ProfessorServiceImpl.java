package br.com.fullStack.education.M1S12.services;

import br.com.fullStack.education.M1S12.entities.ProfessorEntity;
import br.com.fullStack.education.M1S12.exceptions.NotFoundException;
import br.com.fullStack.education.M1S12.repositories.ProfessorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository repository;

    @Override
    public List<ProfessorEntity> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public ProfessorEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Professor não encontrado com id: " + id));
    }

    @Override
    public ProfessorEntity criar(ProfessorEntity entity) {
        entity.setId(null);
        return repository.save(entity);
    }

    @Override
    public ProfessorEntity alterar(Long id, ProfessorEntity entity) {
        buscarPorId(id);
        entity.setId(id);
        return repository.save(entity);
    }

    @Override
    public void excluir(Long id) {
        ProfessorEntity entity = buscarPorId(id);
        repository.delete(entity);
    }
}