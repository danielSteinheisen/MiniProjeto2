package br.com.fullstackeducation.miniprojeto2.controllers;

import br.com.fullstackeducation.miniprojeto2.dto.MediaGeralAlunoDTO;
import br.com.fullstackeducation.miniprojeto2.entities.MatriculaEntity;
import br.com.fullstackeducation.miniprojeto2.services.MatriculaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("matriculas")
public class MatriculaController {

    private final MatriculaService service;

    @GetMapping
    public ResponseEntity<List<MatriculaEntity>> get() {
        log.info("Recebida solicitação para obter todas as matrículas");
        List<MatriculaEntity> matriculas = service.buscarTodos();
        log.info("Retornando todas as matrículas: {}", matriculas);
        return ResponseEntity.ok(matriculas);
    }

    @GetMapping("{id}")
    public ResponseEntity<MatriculaEntity> getId(@PathVariable Long id) {
        log.info("Recebida solicitação para obter a matrícula com id: {}", id);
        MatriculaEntity matricula = service.buscarPorId(id);
        log.info("Retornando a matrícula com id {}: {}", id, matricula);
        return ResponseEntity.ok(matricula);
    }

    @GetMapping("aluno-id/{alunoId}")
    public ResponseEntity<List<MatriculaEntity>> getAlunoId(@PathVariable Long alunoId) {
        log.info("Recebida solicitação para obter as matrículas do aluno com id: {}", alunoId);
        List<MatriculaEntity> matriculas = service.buscarPorAlunoId(alunoId);
        log.info("Retornando as matrículas do aluno com id {}: {}", alunoId, matriculas);
        return ResponseEntity.ok(matriculas);
    }

    @GetMapping("disciplina-id/{disciplinaId}")
    public ResponseEntity<List<MatriculaEntity>> getDisciplinaId(@PathVariable Long disciplinaId) {
        log.info("Recebida solicitação para obter as matrículas da disciplina com id: {}", disciplinaId);
        List<MatriculaEntity> matriculas = service.buscarPorDisciplinaId(disciplinaId);
        log.info("Retornando as matrículas da disciplina com id {}: {}", disciplinaId, matriculas);
        return ResponseEntity.ok(matriculas);
    }

    @GetMapping("aluno-id/{alunoId}/media-geral")
    public ResponseEntity<MediaGeralAlunoDTO> getMediaGeralAluno(@PathVariable Long alunoId) {
        log.info("Recebida solicitação para obter a media geral do aluno com id: {}", alunoId);
        BigDecimal mediaGeralAluno = service.calcularMediaGeralAluno(alunoId);
        log.info("Média geral do aluno com id {}: {}", alunoId, mediaGeralAluno);
        return ResponseEntity.ok(new MediaGeralAlunoDTO(mediaGeralAluno));
    }

    @PostMapping
    public ResponseEntity<MatriculaEntity> post(@RequestBody MatriculaEntity request) {
        log.info("Recebida solicitação para criar uma nova matrícula: {}", request);
        MatriculaEntity matriculaCriada = service.criar(request);
        log.info("Matrícula criada com sucesso: {}", matriculaCriada);
        return ResponseEntity.status(HttpStatus.CREATED).body(matriculaCriada);
    }

    @PutMapping("{id}")
    public ResponseEntity<MatriculaEntity> put(@PathVariable Long id, @RequestBody MatriculaEntity request) {
        log.info("Recebida solicitação para atualizar a matrícula com id: {}: {}", id, request);
        MatriculaEntity matriculaAtualizada = service.alterar(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(matriculaAtualizada);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Recebida solicitação para excluir a matrícula com id: {}", id);
        service.excluir(id);
        log.info("Matrícula com id {} excluída com sucesso!", id);
        return ResponseEntity.noContent().build();
    }
}
