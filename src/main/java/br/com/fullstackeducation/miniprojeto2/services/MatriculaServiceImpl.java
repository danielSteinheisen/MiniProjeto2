package br.com.fullstackeducation.miniprojeto2.services;

import br.com.fullstackeducation.miniprojeto2.entities.AlunoEntity;
import br.com.fullstackeducation.miniprojeto2.entities.DisciplinaEntity;
import br.com.fullstackeducation.miniprojeto2.entities.MatriculaEntity;
import br.com.fullstackeducation.miniprojeto2.exceptions.NotFoundException;
import br.com.fullstackeducation.miniprojeto2.repositories.MatriculaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
@Slf4j
@AllArgsConstructor
@Service
public class MatriculaServiceImpl implements MatriculaService {

    private final MatriculaRepository repository;
    private final AlunoService alunoService;
    private final DisciplinaService disciplinaService;

    @Override
    public List<MatriculaEntity> buscarTodos() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public MatriculaEntity buscarPorId(Long id) {
        MatriculaEntity matricula = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Matricula não encontrada com id: " + id));
        // Carregar explicitamente as notas
        matricula.getNotasMatriculas().size();
        return matricula;
    }

    @Override
    public List<MatriculaEntity> buscarPorAlunoId(Long alunoId) {
        log.info("Buscando todas as matriculas do aluno com id: {}", alunoId);
        alunoService.buscarPorId(alunoId);
        return repository.findByAlunoId(alunoId);
    }

    @Override
    public List<MatriculaEntity> buscarPorDisciplinaId(Long disciplinaId) {
        log.info("Buscando todas as matriculas da disciplina com id: {}", disciplinaId);
        disciplinaService.buscarPorId(disciplinaId);
        return repository.findByDisciplinaId(disciplinaId);
    }

    @Override
    public MatriculaEntity criar(MatriculaEntity entity) {
        log.info("Criando uma nova matricula: {}", entity);
        entity.setId(null);
        AlunoEntity aluno = alunoService.buscarPorId(entity.getAluno().getId());
        entity.setAluno(aluno);
        DisciplinaEntity disciplina = disciplinaService.buscarPorId(entity.getDisciplina().getId());
        entity.setDisciplina(disciplina);

        return repository.save(entity);
    }

    @Override
    public BigDecimal calcularMediaGeralAluno(Long alunoId) {
        log.info("Calculando a média geral do aluno com id: {}", alunoId);
        // Busca todas as matrículas do aluno
        List<MatriculaEntity> matriculas = repository.findByAlunoId(alunoId);
        BigDecimal somaMediasFinais = BigDecimal.ZERO;

        // Calcula a soma das médias finais de todas as disciplinas
        for (MatriculaEntity matricula : matriculas) {
            somaMediasFinais = somaMediasFinais.add(matricula.getMediaFinal());
        }
        // Calcula a média geral
        BigDecimal mediaGeralAluno = BigDecimal.ZERO;
        if (!matriculas.isEmpty()) {
            mediaGeralAluno = somaMediasFinais.divide(BigDecimal.valueOf(matriculas.size()), 2, RoundingMode.HALF_UP);
        }
        return mediaGeralAluno;
    }

    @Override
    public MatriculaEntity alterar(Long id, MatriculaEntity entity) {
        log.info("Alterando a matricula com id: {} para: {}", id, entity);
        buscarPorId(id);
        entity.setId(id);
        return repository.save(entity);
    }

    @Override
    public void excluir(Long id) {
        log.info("Excluindo a matricula com id: {}", id);
        MatriculaEntity entity = buscarPorId(id);

        repository.delete(entity);
    }
}
