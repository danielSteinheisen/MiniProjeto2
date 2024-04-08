package br.com.fullstackeducation.miniprojeto2.exception.error;

public class DisciplinaByNomeNotFoundException extends NotFoundException{

    public DisciplinaByNomeNotFoundException(String nome) {
        super("Aluno não econtrado com nome: " + nome);
    }
}
