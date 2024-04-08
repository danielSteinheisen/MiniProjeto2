package br.com.fullstackeducation.miniprojeto2.exception.error;

public class AlunoByIdNotFoundException extends NotFoundException {

    public AlunoByIdNotFoundException(Long id) {
        super("Aluno não encontrado com id: " + id);
    }
}
