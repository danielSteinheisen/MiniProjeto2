package br.com.fullstackeducation.miniprojeto2.repositories;

import br.com.fullstackeducation.miniprojeto2.entities.NotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaRepository extends JpaRepository<NotaEntity, Long> {
    List<NotaEntity> findByMatriculaId(Long matriculaId);
}
