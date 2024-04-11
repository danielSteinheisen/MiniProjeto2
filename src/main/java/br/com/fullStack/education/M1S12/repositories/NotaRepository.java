package br.com.fullStack.education.M1S12.repositories;

import br.com.fullStack.education.M1S12.entities.NotaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotaRepository extends JpaRepository<NotaEntity, Long> {
    List<NotaEntity> findByDisciplinaId(Long disciplinaId);

}
