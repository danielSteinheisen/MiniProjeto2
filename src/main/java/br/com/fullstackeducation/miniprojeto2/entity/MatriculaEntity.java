package br.com.fullstackeducation.miniprojeto2.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "matricula")
@Entity
@Data
@NoArgsConstructor
public class MatriculaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private AlunoEntity aluno;

    @ManyToOne
    @JoinColumn(name = "disciplina_id")
    private DisciplinaEntity disciplina;

    @JoinColumn(name = "data_matricula")
    private Date dataMatricula;

    @JoinColumn(name = "media_final")
    private float mediaFinal;

}
