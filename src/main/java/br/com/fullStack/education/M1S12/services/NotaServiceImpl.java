package br.com.fullStack.education.M1S12.services;

import br.com.fullStack.education.M1S12.entities.*;
import br.com.fullStack.education.M1S12.exceptions.NotFoundException;
import br.com.fullStack.education.M1S12.repositories.MatriculaRepository;
import br.com.fullStack.education.M1S12.repositories.NotaRepository;
import jdk.jfr.Label;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@AllArgsConstructor
@Service
public class NotaServiceImpl implements NotaService {

    private final NotaRepository repository;
    private final DisciplinaService disciplinaService;
    private final ProfessorService professorService;
    private final MatriculaRepository matriculaRepository;

    @Lazy
    private final MatriculaService matriculaService;

    @Override
    public List<NotaEntity> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public NotaEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nota não encontrada com id: " + id));
    }

    @Override
    public List<NotaEntity> buscarPorDisciplinaId(Long disciplinaId) {
        disciplinaService.buscarPorId(disciplinaId);
        return repository.findByDisciplinaId(disciplinaId);
    }

    @Override
    public NotaEntity criar(NotaEntity entity) {
        entity.setId(null);

        // Verificação se a nota está dentro do intervalo permitido (0 a 10)
        BigDecimal nota = entity.getNota();
        if (nota.compareTo(BigDecimal.ZERO) < 0 || nota.compareTo(BigDecimal.TEN) > 0) {
            throw new NotFoundException("A nota deve estar entre 0 e 10.");
        }

        DisciplinaEntity disciplina = disciplinaService.buscarPorId(entity.getDisciplina().getId());
        entity.setDisciplina(disciplina);

        ProfessorEntity professor = professorService.buscarPorId(disciplina.getProfessor().getId());
        entity.setProfessor(professor);

        // Obter todas as notas relacionadas à disciplina
        List<NotaEntity> notas = repository.findByDisciplinaId(disciplina.getId());

        // Calcular a soma dos produtos de nota e coeficiente
        BigDecimal somaProdutosNotaCoeficiente = BigDecimal.ZERO;
        BigDecimal somaCoeficientes = BigDecimal.ZERO;
        for (NotaEntity notaEntity : notas) {
            somaProdutosNotaCoeficiente = somaProdutosNotaCoeficiente.add(notaEntity.getNota().multiply(notaEntity.getCoeficiente()));
            somaCoeficientes = somaCoeficientes.add(notaEntity.getCoeficiente());
        }

        // Adicionar a nova nota à soma dos produtos de nota e coeficiente
        somaProdutosNotaCoeficiente = somaProdutosNotaCoeficiente.add(nota.multiply(entity.getCoeficiente()));
        somaCoeficientes = somaCoeficientes.add(entity.getCoeficiente());

        // Verificar se a soma dos coeficientes ultrapassa 1.0
        if (somaCoeficientes.compareTo(BigDecimal.ONE) > 0) {
            throw new NotFoundException("A soma dos coeficientes não pode ultrapassar 1.0.");
        }

        matriculaService.calculoMediaFinal(somaCoeficientes, somaProdutosNotaCoeficiente, entity.getDisciplina().getId());

        return repository.save(entity);
    }

    @Override
    public NotaEntity alterar(Long id, NotaEntity entity) {
        buscarPorId(id);
        entity.setId(id);
        return repository.save(entity);
    }

    @Override
    public void excluir(Long id) {
        NotaEntity entity = buscarPorId(id);
        repository.delete(entity);
    }

}
