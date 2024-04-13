package br.com.fullStack.education.M1S12.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "professores")
public class ProfessorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Column(nullable = false, length = 150)
    private String nome;

}
