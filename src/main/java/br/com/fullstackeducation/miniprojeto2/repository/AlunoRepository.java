package br.com.fullstackeducation.miniprojeto2.repository;

import br.com.fullstackeducation.miniprojeto2.entity.AlunoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<AlunoEntity, Long> {
}
