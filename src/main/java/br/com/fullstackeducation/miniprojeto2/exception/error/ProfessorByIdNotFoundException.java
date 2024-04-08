package br.com.fullstackeducation.miniprojeto2.exception.error;

public class ProfessorByIdNotFoundException extends NotFoundException {

    public ProfessorByIdNotFoundException(Long id) {
        super("Professor não encontrado com id: " + id);
    }
}
