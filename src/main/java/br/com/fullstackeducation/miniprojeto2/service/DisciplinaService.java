package br.com.fullstackeducation.miniprojeto2.service;

import br.com.fullstackeducation.miniprojeto2.dto.DisciplinaFiltro;
import br.com.fullstackeducation.miniprojeto2.entity.DisciplinaEntity;

import java.util.List;

public interface DisciplinaService {

    public DisciplinaEntity criarDisciplina(DisciplinaEntity disciplina);

    public List<DisciplinaEntity> listarDisciplinas(DisciplinaFiltro filtro);

    public DisciplinaEntity buscarDisciplinaPorId(Long id);

    public DisciplinaEntity buscarDisciplinaPorNome(String nome);

    public DisciplinaEntity atualizarDisciplina(Long id, DisciplinaEntity disciplina);

    public void excluirDisciplina(Long id);
}
