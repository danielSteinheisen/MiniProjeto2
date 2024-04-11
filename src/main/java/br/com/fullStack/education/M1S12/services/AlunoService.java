package br.com.fullStack.education.M1S12.services;

import br.com.fullStack.education.M1S12.entities.AlunoEntity;

import java.util.List;

public interface AlunoService {

    List<AlunoEntity> buscarTodos();

    AlunoEntity buscarPorId(Long id);

    AlunoEntity criar(AlunoEntity entity);

    AlunoEntity alterar(Long id, AlunoEntity entity);

    void excluir(Long id);

}
