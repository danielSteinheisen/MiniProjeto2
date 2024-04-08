package br.com.fullstackeducation.miniprojeto2.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class AlunoFiltro implements Serializable {

    private String nome;
    private LocalDate nascimento;

}
