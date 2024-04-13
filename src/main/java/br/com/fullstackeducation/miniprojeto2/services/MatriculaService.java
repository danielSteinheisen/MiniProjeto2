package br.com.fullstackeducation.miniprojeto2.services;

import br.com.fullstackeducation.miniprojeto2.entities.MatriculaEntity;

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
