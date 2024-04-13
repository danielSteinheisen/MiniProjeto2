package br.com.fullstackeducation.miniprojeto2.repositories;

import br.com.fullstackeducation.miniprojeto2.entities.DisciplinaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<DisciplinaEntity, Long> {
}
