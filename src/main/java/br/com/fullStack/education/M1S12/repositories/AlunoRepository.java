package br.com.fullStack.education.M1S12.repositories;

import br.com.fullStack.education.M1S12.entities.AlunoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<AlunoEntity, Long> {
}
