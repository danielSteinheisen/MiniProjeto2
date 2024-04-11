package br.com.fullStack.education.M1S12.facade;

import br.com.fullStack.education.M1S12.entities.DisciplinaEntity;
import br.com.fullStack.education.M1S12.entities.MatriculaEntity;
import br.com.fullStack.education.M1S12.entities.NotaEntity;
import br.com.fullStack.education.M1S12.entities.ProfessorEntity;
import br.com.fullStack.education.M1S12.exceptions.NotFoundException;
import br.com.fullStack.education.M1S12.repositories.NotaRepository;
import br.com.fullStack.education.M1S12.services.DisciplinaService;
import br.com.fullStack.education.M1S12.services.MatriculaService;
import br.com.fullStack.education.M1S12.services.NotaService;
import br.com.fullStack.education.M1S12.services.ProfessorService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@AllArgsConstructor
@Service
public class NotaFacadeImpl implements NotaFacade {

    private final NotaService notaService;
    private final MatriculaService matriculaService;
    private final ProfessorService professorService;
    private final DisciplinaService disciplinaService;
    private final NotaRepository notaRepository;

    @Override
    public List<NotaEntity> buscarTodos() {
        return notaService.buscarTodos();
    }

    @Override
    public NotaEntity buscarPorId(Long id) {
        return notaService.buscarPorId(id);
    }

    @Override
    public List<NotaEntity> buscarPorDisciplinaId(Long disciplinaId) {
        return notaService.buscarPorDisciplinaId(disciplinaId);
    }

    @Override
    public NotaEntity criar(NotaEntity entity) {
        return notaService.criar(entity);
    }


    @Override
    public NotaEntity alterar(Long id, NotaEntity entity) {
        return notaService.alterar(id, entity);
    }

    @Override
    public void excluir(Long id) {
        notaService.excluir(id);
    }
}
