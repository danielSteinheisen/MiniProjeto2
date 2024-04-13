package br.com.fullstackeducation.miniprojeto2.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "notas")
public class NotaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @ManyToOne
    @JoinColumn(name = "matricula_id", nullable = false)
    private MatriculaEntity matricula;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private ProfessorEntity professor;

    @Column(precision = 5, scale = 2, nullable = false)
    private BigDecimal nota = BigDecimal.valueOf(0.00);

    @Column(precision = 19, scale = 6, nullable = false)
    private BigDecimal coeficiente = BigDecimal.valueOf(0.00);

}
