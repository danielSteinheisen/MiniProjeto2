package br.com.fullstackeducation.miniprojeto2.repository;

import br.com.fullstackeducation.miniprojeto2.entity.AlunoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoRepository extends JpaRepository<AlunoEntity, Long> {
    List<AlunoEntity> findByNomeContainingIgnoreCase(String nome);
}
