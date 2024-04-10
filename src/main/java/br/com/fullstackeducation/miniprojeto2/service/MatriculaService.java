package br.com.fullstackeducation.miniprojeto2.service;

import br.com.fullstackeducation.miniprojeto2.entity.MatriculaEntity;

import java.util.List;

public interface MatriculaService {

    MatriculaEntity criarMatricula(MatriculaEntity matricula);

    List<MatriculaEntity> listarMatriculas();

    MatriculaEntity buscarMatriculaPorId(Long id);
    MatriculaEntity atualizarMatricula(Long id, MatriculaEntity matricula);

    boolean existeNotaLancada(Long id);

    List<MatriculaEntity> buscarMatriculasPorAluno(Long idAluno);

    List<MatriculaEntity> buscarMatriculasPorDisciplina(Long disciplinaId);
    void excluirMatricula(Long id);

}
