package br.com.fullstackeducation.miniprojeto2.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Data
@Entity
@NoArgsConstructor
@Table(name = "notas")

public class NotaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "matricula_id")
    private MatriculaEntity matricula;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private ProfessorEntity professor;

    @JoinColumn(name = "nota", nullable = false)
    private float nota;

    @ColumnDefault(value = "0.00")
    @Column(precision = 19,scale = 6, nullable = false)
    private BigDecimal coeficiente;
}
