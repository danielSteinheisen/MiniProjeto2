package br.com.fullstackeducation.miniprojeto2.repository;

import br.com.fullstackeducation.miniprojeto2.entity.DisciplinaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<DisciplinaEntity, Long> {
}
