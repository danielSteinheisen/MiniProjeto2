package br.com.fullstackeducation.miniprojeto2.repository;

import br.com.fullstackeducation.miniprojeto2.entity.MatriculaEntity;
import br.com.fullstackeducation.miniprojeto2.entity.NotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaRepository extends JpaRepository<NotaEntity, Long> {
    List<NotaEntity> findAllByMatriculaId (Long matriculaId);

    MatriculaEntity getMatricula(Long id);
}

