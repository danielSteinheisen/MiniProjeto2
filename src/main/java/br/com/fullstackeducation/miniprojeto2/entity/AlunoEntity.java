package br.com.fullstackeducation.miniprojeto2.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "aluno")
@Entity
@Data
@NoArgsConstructor
public class AlunoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private Date nascimento;

    public AlunoEntity(String nome, Date nascimento) {
        this.nome = nome;
        this.nascimento = nascimento;
    }

    public AlunoEntity(Long id, String nome, Date nascimento) {
        this.id = id;
        this.nome = nome;
        this.nascimento = nascimento;
    }
}
