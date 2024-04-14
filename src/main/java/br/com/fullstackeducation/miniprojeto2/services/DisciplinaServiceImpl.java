package br.com.fullstackeducation.miniprojeto2.services;

import br.com.fullstackeducation.miniprojeto2.entities.DisciplinaEntity;
import br.com.fullstackeducation.miniprojeto2.entities.ProfessorEntity;
import br.com.fullstackeducation.miniprojeto2.exceptions.NotFoundException;
import br.com.fullstackeducation.miniprojeto2.repositories.DisciplinaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class DisciplinaServiceImpl implements DisciplinaService {

    private final DisciplinaRepository repository;
    private final ProfessorService professorService;

    @Override
    public List<DisciplinaEntity> buscarTodos() {
        log.info("Buscando todas as disciplinas");
        return repository.findAll();
    }

    @Override
    public DisciplinaEntity buscarPorId(Long id) {
        log.info("Buscando a disciplina com id: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Disciplina não encontrada com id: " + id));
    }

    @Override
    public DisciplinaEntity criar(DisciplinaEntity entity) {
        log.info("Criando uma nova disciplina: {}", entity);
        entity.setId(null);
        ProfessorEntity professor = professorService.buscarPorId(entity.getProfessor().getId());
        entity.setProfessor(professor);

        return repository.save(entity);
    }

    @Override
    public DisciplinaEntity alterar(Long id, DisciplinaEntity entity) {
        log.info("Alterando a disciplina com id: {} para: {}", id, entity);
        buscarPorId(id);
        entity.setId(id);
        return repository.save(entity);
    }

    @Override
    public void excluir(Long id) {
        log.info("Excluindo a disciplina com id: {}", id);
        DisciplinaEntity entity = buscarPorId(id);
        repository.delete(entity);
    }
}
