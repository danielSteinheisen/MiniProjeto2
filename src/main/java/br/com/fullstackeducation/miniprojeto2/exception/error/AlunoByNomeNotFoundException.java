package br.com.fullstackeducation.miniprojeto2.exception.error;

public class AlunoByNomeNotFoundException extends NotFoundException{

    public AlunoByNomeNotFoundException(String nome) {
        super("Aluno não econtrado com nome: " + nome);
    }
}
