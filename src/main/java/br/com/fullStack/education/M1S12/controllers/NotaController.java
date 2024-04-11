package br.com.fullStack.education.M1S12.controllers;

import br.com.fullStack.education.M1S12.entities.NotaEntity;
import br.com.fullStack.education.M1S12.facade.NotaFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("notas")
public class NotaController {

    private final NotaFacade farcade;

    @GetMapping
    public ResponseEntity<List<NotaEntity>> get() {
        return ResponseEntity.ok(farcade.buscarTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<NotaEntity> getId(@PathVariable Long id) {
        return ResponseEntity.ok(farcade.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<NotaEntity> post(@RequestBody NotaEntity request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(farcade.criar(request));
    }

    @GetMapping("disciplina-id/{disciplinaId}")
    public ResponseEntity<List<NotaEntity>> getDisciplinaId(@PathVariable Long disciplinaId) {
        return ResponseEntity.ok(farcade.buscarPorDisciplinaId(disciplinaId));
    }

    @PutMapping("{id}")
    public ResponseEntity<NotaEntity> put(@PathVariable Long id, @RequestBody NotaEntity request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(farcade.alterar(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        farcade.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
