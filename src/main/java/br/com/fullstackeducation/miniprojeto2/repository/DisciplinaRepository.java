package br.com.fullstackeducation.miniprojeto2.repository;

import br.com.fullstackeducation.miniprojeto2.entity.DisciplinaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisciplinaRepository extends JpaRepository<DisciplinaEntity, Long> {
    List<DisciplinaEntity> findByNomeContainingIgnoreCase(String nome);
}
