package br.com.fullstackeducation.miniprojeto2.repositories;

import br.com.fullstackeducation.miniprojeto2.entities.MatriculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<MatriculaEntity, Long> {
    List<MatriculaEntity> findByAlunoId(Long alunoId);
    List<MatriculaEntity> findByDisciplinaId(Long disciplinaId);

}
