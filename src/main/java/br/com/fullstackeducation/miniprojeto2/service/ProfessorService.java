package br.com.fullstackeducation.miniprojeto2.service;

import br.com.fullstackeducation.miniprojeto2.dto.ProfessorFiltro;
import br.com.fullstackeducation.miniprojeto2.entity.ProfessorEntity;

import java.util.List;

public interface ProfessorService {

    ProfessorEntity criarProfessor(ProfessorEntity professor);

    List<ProfessorEntity> listarProfessores(ProfessorFiltro filtro);

    ProfessorEntity buscarProfessorPorId(Long id);
    ProfessorEntity atualizarProfessor(Long id, ProfessorEntity professor);
    void excluirProfessor(Long id);

}
