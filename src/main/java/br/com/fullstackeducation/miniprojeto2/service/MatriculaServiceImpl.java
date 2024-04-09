package br.com.fullstackeducation.miniprojeto2.service;

import br.com.fullstackeducation.miniprojeto2.entity.AlunoEntity;
import br.com.fullstackeducation.miniprojeto2.entity.DisciplinaEntity;
import br.com.fullstackeducation.miniprojeto2.entity.MatriculaEntity;
import br.com.fullstackeducation.miniprojeto2.entity.NotaEntity;
import br.com.fullstackeducation.miniprojeto2.exception.error.NotFoundException;
import br.com.fullstackeducation.miniprojeto2.repository.MatriculaRepository;
import br.com.fullstackeducation.miniprojeto2.repository.NotaRepository;
import br.com.fullstackeducation.miniprojeto2.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Not;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class MatriculaServiceImpl implements MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final AlunoService alunoService;
    private final DisciplinaService disciplinaService;
    private final NotaRepository notaRepository;

    @Override
    public MatriculaEntity criarMatricula(MatriculaEntity matriculaNova) {
        matriculaNova.setId(null);

        log.info("Criando matrícula -> Salvar: \n{}\n", JsonUtil.objetoParaJson(matriculaNova));
        AlunoEntity aluno = alunoService.buscarAlunoPorId(matriculaNova.getAluno().getId());
        matriculaNova.setAluno(aluno);

        DisciplinaEntity disciplina = disciplinaService.buscarDisciplinaPorId(matriculaNova.getDisciplina().getId());
        matriculaNova.setDisciplina(disciplina);

        log.info("Criando matrícula -> Salvo com sucesso");
        log.debug("Criando matrícula -> Registro Salvo: \n{}\n", JsonUtil.objetoParaJson(matriculaNova));
        return matriculaRepository.save(matriculaNova);
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

    /*
    public List<MatriculaEntity> mediaGeralPorAluno(Long idAluno) {

        List<NotaEntity> notas = notaRepository.findBy;


        //notaService.listarNotas().

        return null; //matriculaRepository.findByAlunoId(idAluno);
    }*/

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
}
