package br.com.fullstackeducation.miniprojeto2.services;

import br.com.fullstackeducation.miniprojeto2.entities.ProfessorEntity;

import java.util.List;

public interface ProfessorService {

    List<ProfessorEntity> buscarTodos();

    ProfessorEntity buscarPorId(Long id);

    ProfessorEntity criar(ProfessorEntity entity);

    ProfessorEntity alterar(Long id, ProfessorEntity entity);

    void excluir(Long id);

}
