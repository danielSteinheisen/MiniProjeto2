package br.com.fullstackeducation.miniprojeto2.services;

import br.com.fullstackeducation.miniprojeto2.entities.MatriculaEntity;
import br.com.fullstackeducation.miniprojeto2.entities.NotaEntity;
import br.com.fullstackeducation.miniprojeto2.entities.NotaMatriculaEntity;
import br.com.fullstackeducation.miniprojeto2.exceptions.NotFoundException;
import br.com.fullstackeducation.miniprojeto2.repositories.MatriculaRepository;
import br.com.fullstackeducation.miniprojeto2.repositories.NotaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class NotaServiceImpl implements NotaService {

    private final NotaRepository repository;
    private final MatriculaRepository matriculaRepository;

    @Override
    public List<NotaEntity> buscarTodos() {
        log.info("Buscando todas as notas");
        return repository.findAll();
    }

    @Override
    public NotaEntity buscarPorId(Long id) {
        log.info("Buscando a nota pelo id: {}", id);
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "Nota não encontrada com id: " + id
                ));
    }

    @Override
    public List<NotaEntity> buscarPorMatricula(Long matriculaId) {
        log.info("Buscando todas as notas da matricula com id: {}", matriculaId);
        matriculaRepository.findById(matriculaId)
                .orElseThrow(() -> new NotFoundException("Matrícula não encontrada"));
        return repository.findByMatriculaId(matriculaId);
    }

    @Override
    public NotaEntity criar(NotaEntity entity) {
        log.info("Criando uma nova nota: {}", entity);
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
        log.info("Alterando a nota com id: {} para: {}", id, entity);
        buscarPorId(id);
        entity.setId(id);
        return repository.save(entity);
    }

    @Override
    public void excluir(Long id) {
        log.info("Excluindo a nota com id: {}", id);

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
