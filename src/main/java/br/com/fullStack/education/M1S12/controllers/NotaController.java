package br.com.fullStack.education.M1S12.controllers;

import br.com.fullStack.education.M1S12.entities.NotaEntity;
import br.com.fullStack.education.M1S12.services.NotaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("notas")
public class NotaController {

    private final NotaService service;

    @GetMapping
    public ResponseEntity<List<NotaEntity>> get() {
        return ResponseEntity.ok(service.buscarTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<NotaEntity> getId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @GetMapping("matricula-id/{matriculaId}")
    public ResponseEntity<List<NotaEntity>> getMatriculaId(@PathVariable Long matriculaId) {
        return ResponseEntity.ok(service.buscarPorMatricula(matriculaId));
    }

    @PostMapping
    public ResponseEntity<NotaEntity> post(@RequestBody NotaEntity request) {
        NotaEntity novaNota = service.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaNota);
    }

    @PutMapping("{id}")
    public ResponseEntity<NotaEntity> put(@PathVariable Long id, @RequestBody NotaEntity request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.alterar(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
