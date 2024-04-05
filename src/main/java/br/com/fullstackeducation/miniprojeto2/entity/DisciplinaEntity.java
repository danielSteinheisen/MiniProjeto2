package br.com.fullstackeducation.miniprojeto2.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "disciplina")
@Entity
@Data
@NoArgsConstructor
public class DisciplinaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private ProfessorEntity professor;
}
