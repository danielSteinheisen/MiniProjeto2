package br.com.fullStack.education.M1S12.services;

import br.com.fullStack.education.M1S12.entities.NotaEntity;

import java.util.List;

public interface NotaService {

    List<NotaEntity> buscarTodos();

    NotaEntity buscarPorId(Long id);

    List<NotaEntity> buscarPorDisciplinaId(Long disciplinaId);

    NotaEntity criar(NotaEntity entity);

    NotaEntity alterar(Long id, NotaEntity entity);

    void excluir(Long id);

}
