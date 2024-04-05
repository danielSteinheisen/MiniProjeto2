package br.com.fullstackeducation.miniprojeto2.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "nota")
@Entity
@Data
@NoArgsConstructor
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

    private float nota;
    private double coeficiente;
}
