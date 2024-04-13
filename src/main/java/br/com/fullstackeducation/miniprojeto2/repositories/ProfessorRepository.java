package br.com.fullstackeducation.miniprojeto2.repositories;

import br.com.fullstackeducation.miniprojeto2.entities.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long> {
}
