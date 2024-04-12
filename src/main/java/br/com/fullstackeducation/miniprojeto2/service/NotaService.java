package br.com.fullstackeducation.miniprojeto2.service;

import br.com.fullstackeducation.miniprojeto2.entity.NotaEntity;

import java.math.BigDecimal;
import java.util.List;

public interface NotaService {

    NotaEntity criarNota(Long matriculaId, NotaEntity notaNova, BigDecimal coeficiente);

    NotaEntity criarNota(NotaEntity nota);

    List<NotaEntity> listarNotas();

    NotaEntity buscarNotaPorId(Long id);

    NotaEntity atualizarNota(Long id, NotaEntity nota);

    NotaEntity calcularMedia (Long matriculaId);

    void excluirNota(Long id);

}
