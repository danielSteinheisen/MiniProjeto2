package br.com.fullStack.education.M1S12.controllers;

import br.com.fullStack.education.M1S12.dto.MediaGeralAlunoDTO;
import br.com.fullStack.education.M1S12.entities.MatriculaEntity;
import br.com.fullStack.education.M1S12.services.MatriculaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("matriculas")
public class MatriculaController {

    private final MatriculaService service;

    @GetMapping
    public ResponseEntity<List<MatriculaEntity>> get() {
        return ResponseEntity.ok(service.buscarTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<MatriculaEntity> getId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("aluno-id/{alunoId}")
    public ResponseEntity<List<MatriculaEntity>> getAlunoId(@PathVariable Long alunoId) {
        return ResponseEntity.ok(service.buscarPorAlunoId(alunoId));
    }

    @GetMapping("disciplina-id/{disciplinaId}")
    public ResponseEntity<List<MatriculaEntity>> getDisciplinaId(@PathVariable Long disciplinaId) {
        return ResponseEntity.ok(service.buscarPorDisciplinaId(disciplinaId));
    }

    @GetMapping("aluno-id/{alunoId}/media-geral")
    public ResponseEntity<MediaGeralAlunoDTO> getMediaGeralAluno(@PathVariable Long alunoId) {
        BigDecimal mediaGeralAluno = service.calcularMediaGeralAluno(alunoId);
        return ResponseEntity.ok(new MediaGeralAlunoDTO(mediaGeralAluno));
    }

    @PostMapping
    public ResponseEntity<MatriculaEntity> post(@RequestBody MatriculaEntity request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(request));
    }

    @PutMapping("{id}")
    public ResponseEntity<MatriculaEntity> put(@PathVariable Long id, @RequestBody MatriculaEntity request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.alterar(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
