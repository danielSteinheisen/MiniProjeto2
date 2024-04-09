package br.com.fullstackeducation.miniprojeto2.service;

import br.com.fullstackeducation.miniprojeto2.dto.AlunoFiltro;
import br.com.fullstackeducation.miniprojeto2.entity.AlunoEntity;

import java.util.List;

public interface AlunoService {

    AlunoEntity criarAluno(AlunoEntity aluno);

    List<AlunoEntity> listarAlunos(AlunoFiltro filtro);

    AlunoEntity buscarAlunoPorId(Long id);

    AlunoEntity buscarAlunoPorNome(String nome);

    AlunoEntity atualizarAluno(Long id, AlunoEntity aluno);

    void excluirAluno(Long id);
}
