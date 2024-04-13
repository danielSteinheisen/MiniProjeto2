package br.com.fullStack.education.M1S12.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "nota_matriculas")
public class NotaMatriculaEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "matricula_id", nullable = false)
    private MatriculaEntity matricula;

    @ManyToOne
    @JoinColumn(name = "nota_id", nullable = false)
    private NotaEntity nota;

}
