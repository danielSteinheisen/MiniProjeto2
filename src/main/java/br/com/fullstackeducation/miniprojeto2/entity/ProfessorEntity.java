package br.com.fullstackeducation.miniprojeto2.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "professor")
@Entity
@Data
@NoArgsConstructor
public class ProfessorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    public ProfessorEntity(String nome) {
        this.nome = nome;
    }

    public ProfessorEntity(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }
}
