package br.com.fullStack.education.M1S12.facade;

import br.com.fullStack.education.M1S12.entities.MatriculaEntity;

import java.util.List;

public interface MatriculaFacade {
    List<MatriculaEntity> buscarTodos();

    MatriculaEntity buscarPorId(Long id);

    MatriculaEntity criar(MatriculaEntity entity);

    MatriculaEntity alterar(Long id, MatriculaEntity entity);

    List<MatriculaEntity> buscarPorAlunoId(Long alunoId);

    List<MatriculaEntity> buscarPorDisciplinaId(Long disciplinaId);

    void excluir(Long id);
}
