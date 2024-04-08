package br.com.fullstackeducation.miniprojeto2.service;

import br.com.fullstackeducation.miniprojeto2.dto.AlunoFiltro;
import br.com.fullstackeducation.miniprojeto2.entity.AlunoEntity;

import java.util.List;

public interface AlunoService {

    //CRUD

    AlunoEntity criar(AlunoEntity aluno) throws Exception; //Create

    List<AlunoEntity> buscarTodos(AlunoFiltro filtro); //Read

    AlunoEntity buscarPorId(Long id) throws Exception; //Read

    AlunoEntity buscarPorNome(String nome) throws Exception;

    AlunoEntity alterar(Long id, AlunoEntity aluno) throws Exception; //Update

    void apagar(Long id) throws Exception; //Delete
}
