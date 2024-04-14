package br.com.fullstackeducation.miniprojeto2.services;

import br.com.fullstackeducation.miniprojeto2.entities.ProfessorEntity;
import br.com.fullstackeducation.miniprojeto2.exceptions.NotFoundException;
import br.com.fullstackeducation.miniprojeto2.repositories.ProfessorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class ProfessorServiceImpl implements ProfessorService {

    private final ProfessorRepository repository;

    @Override
    public List<ProfessorEntity> buscarTodos() {
        log.info("Buscando todos os professores");
        return repository.findAll();
    }

    @Override
    public ProfessorEntity buscarPorId(Long id) {
        log.info("Buscando um professor pelo id: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "Professor não encontrado com id: " + id
                ));
    }

    @Override
    public ProfessorEntity criar(ProfessorEntity entity) {
        log.info("Criando um novo professor: {}", entity);
        entity.setId(null);
        return repository.save(entity);
    }

    @Override
    public ProfessorEntity alterar(Long id, ProfessorEntity entity) {
        log.info("Alterando o professor com id: {}, para: {}", id, entity);
        buscarPorId(id);
        entity.setId(id);
        return repository.save(entity);
    }

    @Override
    public void excluir(Long id) {
        log.info("Excluindo o professor com id: {}", id);
        ProfessorEntity entity = buscarPorId(id);
        repository.delete(entity);
    }
}
