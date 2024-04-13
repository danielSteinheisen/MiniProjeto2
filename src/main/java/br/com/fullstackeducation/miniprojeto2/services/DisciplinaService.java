package br.com.fullstackeducation.miniprojeto2.services;

import br.com.fullstackeducation.miniprojeto2.entities.DisciplinaEntity;

import java.util.List;

public interface DisciplinaService {

    List<DisciplinaEntity> buscarTodos();

    DisciplinaEntity buscarPorId(Long id);

    DisciplinaEntity criar(DisciplinaEntity entity);

    DisciplinaEntity alterar(Long id, DisciplinaEntity entity);

    void excluir(Long id);

}
