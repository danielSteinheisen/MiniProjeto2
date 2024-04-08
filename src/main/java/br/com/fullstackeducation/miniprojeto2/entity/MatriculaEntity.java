package br.com.fullstackeducation.miniprojeto2.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "matriculas")
public class MatriculaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private AlunoEntity aluno;

    @ManyToOne
    @JoinColumn(name = "disciplina_id", nullable = false)
    private DisciplinaEntity disciplina;

    @JoinColumn(name = "data_matricula", nullable = false)
    private Date dataMatricula;

    @ColumnDefault(value = "0.00")
    @Column(name="media_final", precision = 5,scale = 2,nullable = false)
    @JoinColumn(name = "media_final")
    private BigDecimal mediaFinal;

    @JoinColumn(name = "aluno_id", nullable = false)
    private Long AlunoId;

    private Long DisciplinaId;

    public Collection<Object> getNotas() {
        return null;
    }
}
