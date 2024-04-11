package br.com.fullStack.education.M1S12.facade;

import br.com.fullStack.education.M1S12.entities.MatriculaEntity;
import br.com.fullStack.education.M1S12.services.MatriculaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MatriculaFacadeImpl implements MatriculaFacade {

    private final MatriculaService matriculaService;

    @Override
    public List<MatriculaEntity> buscarTodos() {
        return matriculaService.buscarTodos();
    }

    @Override
    public MatriculaEntity buscarPorId(Long id) {
        return matriculaService.buscarPorId(id);
    }

    @Override
    public MatriculaEntity criar(MatriculaEntity entity) {
        return matriculaService.criar(entity);
    }

    @Override
    public MatriculaEntity alterar(Long id, MatriculaEntity entity) {
        return matriculaService.alterar(id, entity);
    }

    @Override
    public List<MatriculaEntity> buscarPorAlunoId(Long alunoId) {
        return matriculaService.buscarPorAlunoId(alunoId);
    }

    @Override
    public List<MatriculaEntity> buscarPorDisciplinaId(Long disciplinaId) {
        return matriculaService.buscarPorDisciplinaId(disciplinaId);
    }

    @Override
    public void excluir(Long id) {
        matriculaService.excluir(id);
    }
}
