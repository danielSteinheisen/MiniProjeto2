package br.com.fullStack.education.M1S12.controllers;

import br.com.fullStack.education.M1S12.entities.MatriculaEntity;
import br.com.fullStack.education.M1S12.facade.MatriculaFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("matriculas")
public class MatriculaController {

    private final MatriculaFacade facade;

    @GetMapping
    public ResponseEntity<List<MatriculaEntity>> get() {
        return ResponseEntity.ok(facade.buscarTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<MatriculaEntity> getId(@PathVariable Long id) {
        return ResponseEntity.ok(facade.buscarPorId(id));
    }

    @GetMapping("aluno-id/{alunoId}")
    public ResponseEntity<List<MatriculaEntity>> getAlunoId(@PathVariable Long alunoId) {
        return ResponseEntity.ok(facade.buscarPorAlunoId(alunoId));
    }

    @GetMapping("disciplina-id/{disciplinaId}")
    public ResponseEntity<List<MatriculaEntity>> getDisciplinaId(@PathVariable Long disciplinaId) {
        return ResponseEntity.ok(facade.buscarPorDisciplinaId(disciplinaId));
    }

    @PostMapping
    public ResponseEntity<MatriculaEntity> post(@RequestBody MatriculaEntity request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(facade.criar(request));
    }

    @PutMapping("{id}")
    public ResponseEntity<MatriculaEntity> put(@PathVariable Long id, @RequestBody MatriculaEntity request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(facade.alterar(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        facade.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
