package br.com.fullstackeducation.miniprojeto2.repository;

import br.com.fullstackeducation.miniprojeto2.entity.MatriculaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<MatriculaEntity, Long> {
    List<MatriculaEntity> findByAlunoId(Long idAluno);
    List<MatriculaEntity> findByDisciplinaId(Long idDisciplina);
}
