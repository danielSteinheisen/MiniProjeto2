package br.com.fullStack.education.M1S12.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "matriculas")
public class MatriculaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private AlunoEntity aluno;

    @ManyToOne
    @JoinColumn(name = "disciplina_id", nullable = false)
    private DisciplinaEntity disciplina;

    @Column(nullable = false)
    private LocalDate dataMatricula = LocalDate.now();

    @Column(precision = 5, scale = 2, nullable = false)
    private BigDecimal mediaFinal = BigDecimal.valueOf(0.00);

    //@OneToMany(mappedBy = "matricula")
    //private List<NotaEntity> notas;
}
