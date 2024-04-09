package br.com.fullstackeducation.miniprojeto2.controller;

import br.com.fullstackeducation.miniprojeto2.entity.DisciplinaEntity;
import br.com.fullstackeducation.miniprojeto2.entity.MatriculaEntity;
import br.com.fullstackeducation.miniprojeto2.service.MatriculaServiceImpl;
import br.com.fullstackeducation.miniprojeto2.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/matriculas")
public class MatriculaController {

    private final MatriculaServiceImpl matriculaServiceImpl;

    public MatriculaController(MatriculaServiceImpl matriculaServiceImpl) {
        this.matriculaServiceImpl = matriculaServiceImpl;
    }
    @PostMapping
    public ResponseEntity<MatriculaEntity> criarMatricula(@RequestBody MatriculaEntity matriculaRequisicao) {
        log.info("POST /Matrícula -> Início");

        MatriculaEntity matricula = matriculaServiceImpl.criarMatricula(matriculaRequisicao);
        log.info("POST /Matrícula -> Cadastrada");
        log.info("POST /Matrícula -> 201 CREATED");
        log.info("POST /Matrícula -> Response Body: \n{}\n", JsonUtil.objetoParaJson(matricula));
        return ResponseEntity.status(HttpStatus.CREATED).body(matricula);
    }

    @GetMapping
    public ResponseEntity<List<MatriculaEntity>> listarMatriculas() {
        log.info("GET/Matrículas -> Início");

        List<MatriculaEntity> matriculas = matriculaServiceImpl.listarMatriculas();
        log.info("GET/Matrículas -> Encontrados {} registros", matriculas.size());
        log.info("GET/Matrículas  -> 200 ok");
        log.info("GET/Matrículas  -> Response Body: \n{}\n", JsonUtil.objetoParaJson(matriculas));
        return ResponseEntity.status(HttpStatus.OK).body(matriculas);
    }
    @GetMapping("/{id}")
    public ResponseEntity<MatriculaEntity> buscarMatriculaPorId(@PathVariable Long id) {
        log.info("GET /Matrículas/{} -> Início", id);
        MatriculaEntity matricula = matriculaServiceImpl.buscarMatriculaPorId(id);
        log.info("GET /Matrículas/{} -> Encontradas", id);
        log.info("GET /Matrículas/{} -> 200 OK", id);
        log.debug("GET /Matrículas/{} -> Response Body:\n{}\n", id, JsonUtil
                .objetoParaJson(matriculaServiceImpl.buscarMatriculaPorId(id)));
        return ResponseEntity.ok(matriculaServiceImpl.buscarMatriculaPorId(id));
    }
    @GetMapping("/aluno/{alunoId}")
    public ResponseEntity<List<MatriculaEntity>> buscarMatriculasPorAluno(@PathVariable Long alunoId) {
        List<MatriculaEntity> matriculas = matriculaServiceImpl.buscarMatriculasPorAluno(alunoId);
        log.info("GET /Matrículas/aluno/{} -> Início", alunoId);
        log.info("GET /Matrículas/aluno/{} -> Encontradas", alunoId);
        log.info("GET /Matrículas/aluno/{} -> 200 OK", alunoId);
        log.debug("GET /Matrículas/aluno/{} -> Response Body:\n{}\n", alunoId, JsonUtil
                .objetoParaJson(matriculas));
        return ResponseEntity.ok(matriculas);
    }
    @GetMapping("/disciplina/{disciplinaId}")
    public ResponseEntity<List<MatriculaEntity>> buscarMatriculasPorDisciplina(@PathVariable Long disciplinaId) {
        List<MatriculaEntity> matriculas = matriculaServiceImpl.buscarMatriculasPorDisciplina(disciplinaId);
        log.info("GET /Matrículas/disciplina/{} -> Início", disciplinaId);
        log.info("GET /Matrículas/disciplina/{} -> Encontradas", disciplinaId);
        log.info("GET /Matrículas/disciplina/{} -> 200 OK", disciplinaId);
        log.debug("GET /Matrículas/disciplina/{} -> Response Body:\n{}\n", disciplinaId, JsonUtil
                .objetoParaJson(matriculas));
        return ResponseEntity.ok(matriculas);

    }
    @PutMapping("/{id}")
    public ResponseEntity<MatriculaEntity> atualizarMatricula(@PathVariable Long id, @RequestBody MatriculaEntity matricula) {
        log.info("PUT /Matrículas/{}", id);
        log.info("PUT /Matrículas/{} -> Atualizada", id);
        log.info("PUT /Matrículas/{} -> 200 OK", id);
        log.debug("PUT /Matrículas/{} -> Response Body:\n{}\n", id, JsonUtil
                .objetoParaJson(matriculaServiceImpl.atualizarMatricula(id, matricula)));
        return ResponseEntity.ok(matriculaServiceImpl.atualizarMatricula(id, matricula));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluirMatricula(@PathVariable Long id) {
        log.info("DELETE /Matrículas/{}", id);
        Long matriculaId = null;
        if (matriculaServiceImpl.existeNotaLancada(matriculaId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body
                    ("Não é possível excluir a matrícula, pois existem notas lançadas.");
        }
        else {
            matriculaServiceImpl.excluirMatricula(matriculaId);
        log.info("DELETE /Matrículas/{} -> Excluída", id);
        log.info("DELETE /Matrículas/{} -> 204 No Content", id);
        matriculaServiceImpl.excluirMatricula(id);
        return ResponseEntity.noContent().build();
        }
    }
}