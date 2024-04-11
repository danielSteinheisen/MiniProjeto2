package br.com.fullStack.education.M1S12.controllers;

import br.com.fullStack.education.M1S12.entities.DisciplinaEntity;
import br.com.fullStack.education.M1S12.services.DisciplinaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("disciplinas")
public class DisciplinaController {

    private final DisciplinaService service;

    @GetMapping
    public ResponseEntity<List<DisciplinaEntity>> get() {
        return ResponseEntity.ok(service.buscarTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<DisciplinaEntity> getId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<DisciplinaEntity> post(@RequestBody DisciplinaEntity request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(request));
    }

    @PutMapping("{id}")
    public ResponseEntity<DisciplinaEntity> put(@PathVariable Long id, @RequestBody DisciplinaEntity request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.alterar(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
