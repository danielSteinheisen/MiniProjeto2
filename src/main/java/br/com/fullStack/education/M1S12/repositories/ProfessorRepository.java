package br.com.fullStack.education.M1S12.repositories;

import br.com.fullStack.education.M1S12.entities.ProfessorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long> {
}
