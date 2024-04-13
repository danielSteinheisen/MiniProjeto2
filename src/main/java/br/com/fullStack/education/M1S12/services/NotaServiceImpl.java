package br.com.fullStack.education.M1S12.services;

import br.com.fullStack.education.M1S12.entities.*;
import br.com.fullStack.education.M1S12.exceptions.NotFoundException;
import br.com.fullStack.education.M1S12.repositories.MatriculaRepository;
import br.com.fullStack.education.M1S12.repositories.NotaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Service
public class NotaServiceImpl implements NotaService {

    private final NotaRepository repository;
    private final MatriculaRepository matriculaRepository;

    @Override
    public List<NotaEntity> buscarTodos() {
        return repository.findAll();
    }

    @Override
    public NotaEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Nota não encontrada com id: " + id));
    }

    @Override
    public List<NotaEntity> buscarPorMatricula(Long matriculaId) {
        matriculaRepository.findById(matriculaId)
                .orElseThrow(() -> new NotFoundException("Matrícula não encontrada"));
        return repository.findByMatriculaId(matriculaId);
    }

    @Override
    public NotaEntity criar(NotaEntity entity) {
        // Obtém a matrícula com base no "ID" fornecido
        MatriculaEntity matricula = matriculaRepository.findById(entity.getMatricula().getId())
                .orElseThrow(() -> new NotFoundException("Matrícula não encontrada"));

        // Verifica se a nota está entre 0 e 10
        if (entity.getNota().compareTo(BigDecimal.ZERO) < 0 || entity.getNota().compareTo(BigDecimal.TEN) > 0) {
            throw new NotFoundException("A nota deve estar entre 0 e 10");
        }

        // Verifica se o coeficiente está entre 0 e 1
        if (entity.getCoeficiente().compareTo(BigDecimal.ZERO) < 0 || entity.getCoeficiente().compareTo(BigDecimal.ONE) > 0) {
            throw new NotFoundException("O coeficiente deve estar entre 0 e 1");
        }

        // Calcula o produto da nota e coeficiente da nova nota
        BigDecimal novaNotaProduto = entity.getNota().multiply(entity.getCoeficiente());

        // Calcula a soma da nota final atual e a nota nova
        BigDecimal novaMediaFinal = matricula.getMediaFinal().add(novaNotaProduto);

        // Verifica se a soma das notas excede 10
        if (novaMediaFinal.compareTo(BigDecimal.TEN) > 0) {
            throw new NotFoundException("A soma da nota final com a nova nota não pode exceder 10");
        }

        // Cria uma nota com os dados fornecidos
        NotaEntity novaNota = new NotaEntity();
        novaNota.setMatricula(matricula);
        novaNota.setProfessor(matricula.getDisciplina().getProfessor());
        novaNota.setNota(entity.getNota());
        novaNota.setCoeficiente(entity.getCoeficiente());

        // Salva a nova nota no banco de dados
        novaNota = repository.save(novaNota);

        // Atualiza a média final na matrícula
        matricula.setMediaFinal(novaMediaFinal);

        // Cria a relação entre a nova nota e a matrícula
        NotaMatriculaEntity notaMatricula = new NotaMatriculaEntity();
        notaMatricula.setMatricula(matricula);
        notaMatricula.setNota(novaNota);
        matricula.getNotasMatriculas().add(notaMatricula);

        // Salva a matrícula atualizada no banco de dados
        matriculaRepository.save(matricula);

        return novaNota;
    }



    @Override
    public NotaEntity alterar(Long id, NotaEntity entity) {
        buscarPorId(id);
        entity.setId(id);
        return repository.save(entity);
    }

    @Override
    public void excluir(Long id) {

        // Busca a nota pelo ID
        NotaEntity nota = buscarPorId(id);

        // Obtém a matrícula relacionada à nota
        MatriculaEntity matricula = matriculaRepository.findById(nota.getMatricula().getId())
                .orElseThrow(() -> new NotFoundException("Matrícula não encontrada"));

        // Calcula o produto da nota e coeficiente da nota a ser excluída
        BigDecimal notaProduto = nota.getNota().multiply(nota.getCoeficiente());

        // Subtrai o produto da nota e coeficiente da notaFinal da matrícula
        BigDecimal novaNotaFinal = matricula.getMediaFinal().subtract(notaProduto);

        // Atualiza a notaFinal da matrícula
        matricula.setMediaFinal(novaNotaFinal);
        matriculaRepository.save(matricula);

        // Remove a nota da lista de notas da matrícula
        matricula.getNotasMatriculas().removeIf(n -> n.getNota().equals(nota));

        // Remove a nota do repositório
        repository.delete(nota);
    }
}
