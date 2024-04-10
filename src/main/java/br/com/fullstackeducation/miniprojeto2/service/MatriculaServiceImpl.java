package br.com.fullstackeducation.miniprojeto2.service;

import br.com.fullstackeducation.miniprojeto2.entity.AlunoEntity;
import br.com.fullstackeducation.miniprojeto2.entity.DisciplinaEntity;
import br.com.fullstackeducation.miniprojeto2.entity.MatriculaEntity;
import br.com.fullstackeducation.miniprojeto2.exception.NotFoundException;
import br.com.fullstackeducation.miniprojeto2.repository.AlunoRepository;
import br.com.fullstackeducation.miniprojeto2.repository.DisciplinaRepository;
import br.com.fullstackeducation.miniprojeto2.repository.MatriculaRepository;
import br.com.fullstackeducation.miniprojeto2.repository.NotaRepository;
import br.com.fullstackeducation.miniprojeto2.util.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class MatriculaServiceImpl implements MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final AlunoRepository alunoRepository;
    private final DisciplinaRepository disciplinaRepository;
    private final NotaRepository notaRepository;

    @Override
    public MatriculaEntity criarMatricula(MatriculaEntity matriculaNova) {
        matriculaNova.setId(null);
        log.info("Criando matrícula -> Salvar: \n{}\n", JsonUtil.objetoParaJson(matriculaNova));
        MatriculaEntity matricula = matriculaRepository.save(matriculaNova);
        log.info("Criando matrícula -> Salvo com sucesso");
        log.debug("Criando matrícula -> Registro Salvo: \n{}\n", JsonUtil.objetoParaJson(matriculaNova));
        return matricula;
    }

    @Override
    public List<MatriculaEntity> listarMatriculas() {
        log.info("Buscando todas as matriculas");
        List<MatriculaEntity> matriculas = matriculaRepository.findAll();
        log.info("Buscando todas as matrículas -> {} matrículas encontradas", matriculaRepository.findAll().size());
        log.debug("Buscando todas as matrículas -> Registros encontrados:\n{}\n", JsonUtil.objetoParaJson(matriculaRepository));
        return matriculas;
    }

    @Override
    public MatriculaEntity buscarMatriculaPorId(Long id) {
        log.info("Buscando matrícula por ID: {}", id);
        Optional<MatriculaEntity> matricula = matriculaRepository.findById(id);
        if (matricula.isEmpty()) {
            log.error("Buscando matrícula por id {} -> NÃO Encontrado", id);
            return matriculaRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Matrícula não encontrada com o ID: " + id));
        }
        log.info("Buscando matrícula por id ({}) -> Encontrado", id);
        log.debug("Buscando matrícula por id ({}) -> Registro encontrado:\n{}\n", id, JsonUtil.objetoParaJson(matricula.get()));
        return matricula.get();
    }

    @Override
    public MatriculaEntity atualizarMatricula(Long id, MatriculaEntity matricula) {
        if (!matriculaRepository.existsById(id)) {
            log.info("Alterando matrícula com id ({}) -> Salvar: \n{}\n", id, JsonUtil.objetoParaJson(matricula));
            throw new NotFoundException("Matrícula não encontrada com o ID: " + id);
        }
        matricula.setId(id);
        log.info("Alterando matrícula -> Salvo com sucesso");
        log.debug("Alterando matrícula -> Registro Salvo: \n{}\n", JsonUtil.objetoParaJson(matricula));
        return matriculaRepository.save(matricula);
    }

    @Override
    public boolean existeNotaLancada(Long id) {
        MatriculaEntity matricula = matriculaRepository.findById(id).orElseThrow(() ->
                new NotFoundException("Matrícula não encontrada"));

        return !matricula.getNotas().isEmpty();
    }

    @Override
    public List<MatriculaEntity> buscarMatriculasPorAluno(Long idAluno) {
        return matriculaRepository.findByAlunoId(idAluno);
    }

    @Override
    public List<MatriculaEntity> buscarMatriculasPorDisciplina(Long disciplinaId) {
        return matriculaRepository.findByDisciplinaId(disciplinaId);
    }

    @Override
    public void excluirMatricula(Long id) {
        if (!matriculaRepository.existsById(id)) {
            log.info("Excluindo matrícula com id ({}) -> Excluindo", id);
            throw new NotFoundException("Matrícula não encontrada com o ID: " + id);
        }
        matriculaRepository.deleteById(id);
        log.info("Excluindo matrícula com id ({}) -> Excluído com sucesso", id);
    }

    public MatriculaEntity matricularAlunoEmDisciplina(Long alunoId, Long disciplinaId) {
        AlunoEntity aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));

        DisciplinaEntity disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new NotFoundException("Disciplina não encontrada"));

        MatriculaEntity matricula = new MatriculaEntity();
        matricula.setAluno(aluno);
        matricula.setDisciplina(disciplina);
        return matriculaRepository.save(matricula);
    }

}
