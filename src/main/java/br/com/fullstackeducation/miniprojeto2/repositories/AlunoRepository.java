package br.com.fullstackeducation.miniprojeto2.repositories;


import br.com.fullstackeducation.miniprojeto2.entities.AlunoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<AlunoEntity, Long> {
}
