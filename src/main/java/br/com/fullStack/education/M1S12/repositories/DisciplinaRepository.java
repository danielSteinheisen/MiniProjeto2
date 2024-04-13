package br.com.fullStack.education.M1S12.repositories;

import br.com.fullStack.education.M1S12.entities.DisciplinaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<DisciplinaEntity, Long> {
}
