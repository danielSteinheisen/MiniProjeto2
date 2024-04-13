package br.com.fullStack.education.M1S12.services;

import br.com.fullStack.education.M1S12.entities.DisciplinaEntity;
import br.com.fullStack.education.M1S12.entities.ProfessorEntity;
import br.com.fullStack.education.M1S12.exceptions.NotFoundException;
import br.com.fullStack.education.M1S12.repositories.DisciplinaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DisciplinaServiceImpl implements DisciplinaService {

    private final DisciplinaRepository repository;
    private final ProfessorService professorService;

    @Override
    public List<DisciplinaEntity> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public DisciplinaEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Disciplina não encontrada com id: " + id));
    }

    @Override
    public DisciplinaEntity criar(DisciplinaEntity entity) {
        entity.setId(null);

        ProfessorEntity professor = professorService.buscarPorId(entity.getProfessor().getId());
        entity.setProfessor(professor);

        return repository.save(entity);
    }

    @Override
    public DisciplinaEntity alterar(Long id, DisciplinaEntity entity) {
        buscarPorId(id);
        entity.setId(id);
        return repository.save(entity);
    }

    @Override
    public void excluir(Long id) {
        DisciplinaEntity entity = buscarPorId(id);
        repository.delete(entity);
    }
}