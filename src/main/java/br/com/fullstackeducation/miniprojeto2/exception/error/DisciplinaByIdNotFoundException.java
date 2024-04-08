package br.com.fullstackeducation.miniprojeto2.exception.error;

public class DisciplinaByIdNotFoundException extends NotFoundException {

    public DisciplinaByIdNotFoundException(Long id) {
        super("Aluno não encontrado com id: " + id);
    }
}
