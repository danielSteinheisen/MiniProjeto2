package br.com.fullStack.education.M1S12.services;

import br.com.fullStack.education.M1S12.entities.*;
import br.com.fullStack.education.M1S12.exceptions.NotFoundException;
import br.com.fullStack.education.M1S12.repositories.MatriculaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@AllArgsConstructor
@Service
public class MatriculaServiceImpl implements MatriculaService {

    private final MatriculaRepository repository;
    private final AlunoService alunoService;
    private final DisciplinaService disciplinaService;
    private final NotaService notaService;

    @Override
    public List<MatriculaEntity> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public MatriculaEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Matricula não encontrada com id: " + id));
    }

    @Override
    public List<MatriculaEntity> buscarPorAlunoId(Long alunoId) {
        alunoService.buscarPorId(alunoId);
        return repository.findByAlunoId(alunoId);
    }

    @Override
    public List<MatriculaEntity> buscarPorDisciplinaId(Long disciplinaId) {
        disciplinaService.buscarPorId(disciplinaId);
        return repository.findByDisciplinaId(disciplinaId);
    }

    @Override
    public MatriculaEntity criar(MatriculaEntity entity) {
        entity.setId(null);

        AlunoEntity aluno = alunoService.buscarPorId(entity.getAluno().getId());
        entity.setAluno(aluno);

        DisciplinaEntity disciplina = disciplinaService.buscarPorId(entity.getDisciplina().getId());
        entity.setDisciplina(disciplina);

        return repository.save(entity);
    }

    @Override
    public MatriculaEntity alterar(Long id, MatriculaEntity entity) {
        buscarPorId(id);
        entity.setId(id);
        return repository.save(entity);
    }

    @Override
    public void excluir(Long id) {
        MatriculaEntity entity = buscarPorId(id);

        notaService.buscarPorDisciplinaId(entity.getDisciplina().getId());

        repository.delete(entity);
    }

    public void calculoMediaFinal(BigDecimal somaCoeficientes,
                                  BigDecimal somaProdutosNotaCoeficiente,
                                  Long disciplinaId) {
        BigDecimal mediaFinal;
        if (somaCoeficientes.compareTo(BigDecimal.ZERO) == 0) {
            mediaFinal = BigDecimal.ZERO;
        } else {
            mediaFinal = somaProdutosNotaCoeficiente.divide(somaCoeficientes, 2, RoundingMode.HALF_UP);
        }
        atualizarMediaFinal(mediaFinal, disciplinaId);
    }

    private void atualizarMediaFinal (BigDecimal mediaFinal, Long disciplinaId) {
        List<MatriculaEntity> matriculas = buscarPorDisciplinaId(disciplinaId);
        for (MatriculaEntity matricula : matriculas) {
            matricula.setMediaFinal(mediaFinal);
            alterar(matricula.getId(), matricula);
        }
    }
}
