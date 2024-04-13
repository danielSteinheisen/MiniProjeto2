package br.com.fullStack.education.M1S12.services;

import br.com.fullStack.education.M1S12.entities.MatriculaEntity;

import java.math.BigDecimal;
import java.util.List;

public interface MatriculaService {

    List<MatriculaEntity> buscarTodos();

    MatriculaEntity buscarPorId(Long id);

    List<MatriculaEntity> buscarPorAlunoId(Long alunoId);

    List<MatriculaEntity> buscarPorDisciplinaId(Long disciplinaId);

    BigDecimal calcularMediaGeralAluno(Long alunoId);


    MatriculaEntity criar(MatriculaEntity entity);

    MatriculaEntity alterar(Long id, MatriculaEntity entity);

    void excluir(Long id);
}
