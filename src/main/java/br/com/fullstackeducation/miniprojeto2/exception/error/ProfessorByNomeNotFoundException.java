package br.com.fullstackeducation.miniprojeto2.exception.error;

public class ProfessorByNomeNotFoundException extends NotFoundException{

    public ProfessorByNomeNotFoundException(String nome) {
        super("Professor não econtrado com nome: " + nome);
    }
}
