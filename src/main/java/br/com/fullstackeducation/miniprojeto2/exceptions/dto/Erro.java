package br.com.fullstackeducation.miniprojeto2.exceptions.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Erro {
    private String codigo;
    private String mensagem;
}