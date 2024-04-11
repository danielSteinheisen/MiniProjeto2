package br.com.fullStack.education.M1S12.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "notas")
public class NotaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @ManyToOne
    @JoinColumn(name = "disciplina_id", nullable = false)
    private DisciplinaEntity disciplina;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private ProfessorEntity professor;

    @Column(precision = 5, scale = 2, nullable = false)
    private BigDecimal nota = BigDecimal.valueOf(0.00);

    @Column(precision = 19, scale = 6, nullable = false)
    private BigDecimal coeficiente = BigDecimal.valueOf(0.00);

}
