package br.com.fullstackeducation.miniprojeto2.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "matriculas")
public class MatriculaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private AlunoEntity aluno;

    @ManyToOne
    @JoinColumn(name = "disciplina_id", nullable = false)
    private DisciplinaEntity disciplina;

    @Column(name = "data_matricula", nullable = false)
    private Date dataMatricula = new Date();

    @Column(name="media_final", precision = 5,scale = 2, nullable = false)
    @JoinColumn(name = "media_final")
    private BigDecimal mediaFinal = BigDecimal.valueOf(10.00);

    public Collection<Object> getNotas() {
        return null;
    }
}
