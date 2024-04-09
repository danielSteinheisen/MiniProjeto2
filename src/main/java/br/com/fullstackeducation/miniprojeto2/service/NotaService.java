package br.com.fullstackeducation.miniprojeto2.service;

import br.com.fullstackeducation.miniprojeto2.entity.NotaEntity;

import java.util.List;

public interface NotaService {

    NotaEntity criarNota(NotaEntity nota);

    List<NotaEntity> listarNotas();

    NotaEntity buscarNotaPorId(Long id);

    NotaEntity atualizarNota(Long id, NotaEntity nota);

    NotaEntity calcularMedia (Long matriculaId);

    void excluirNota(Long id);
}
