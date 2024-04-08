package br.com.fullstackeducation.miniprojeto2.repository;

import br.com.fullstackeducation.miniprojeto2.entity.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long> {
}
