package br.com.fullStack.education.M1S12.services;

import br.com.fullStack.education.M1S12.entities.DisciplinaEntity;

import java.util.List;

public interface DisciplinaService {

    List<DisciplinaEntity> buscarTodos();

    DisciplinaEntity buscarPorId(Long id);

    DisciplinaEntity criar(DisciplinaEntity entity);

    DisciplinaEntity alterar(Long id, DisciplinaEntity entity);

    void excluir(Long id);

}
