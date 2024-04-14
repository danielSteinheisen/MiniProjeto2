package br.com.fullstackeducation.miniprojeto2.controllers;

import br.com.fullstackeducation.miniprojeto2.entities.DisciplinaEntity;
import br.com.fullstackeducation.miniprojeto2.services.DisciplinaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("disciplinas")
public class DisciplinaController {

    private final DisciplinaService service;

    @GetMapping
    public ResponseEntity<List<DisciplinaEntity>> get() {
        log.info("Recebida solicitação para obter todas as disciplinas");
        List<DisciplinaEntity> disciplinas = service.buscarTodos();
        log.info("Retornando todas as disciplinas: {}", disciplinas);
        return ResponseEntity.ok(disciplinas);
    }

    @GetMapping("{id}")
    public ResponseEntity<DisciplinaEntity> getId(@PathVariable Long id) {
        log.info("Recebida solicitação para obter a disciplina com id: {}", id);
        DisciplinaEntity disciplina = service.buscarPorId(id);
        log.info("Retornando a disciplina com id {}: {}", id, disciplina);
        return ResponseEntity.ok(disciplina);
    }

    @PostMapping
    public ResponseEntity<DisciplinaEntity> post(@RequestBody DisciplinaEntity request) {
        log.info("Recebida solicitação para criar uma nova disciplina: {}", request);
        DisciplinaEntity disciplinaCriada = service.criar(request);
        log.info("Disciplina criada com sucesso: {}", disciplinaCriada);
        return ResponseEntity.status(HttpStatus.CREATED).body(disciplinaCriada);
    }

    @PutMapping("{id}")
    public ResponseEntity<DisciplinaEntity> put(@PathVariable Long id, @RequestBody DisciplinaEntity request) {
        log.info("Recebida solicitação para atualizar a disciplina com id {}: {}", id, request);
        DisciplinaEntity disciplinaAtualizada = service.alterar(id, request);
        log.info("Disciplina atualizada com sucesso: {}", disciplinaAtualizada);
        return ResponseEntity.status(HttpStatus.CREATED).body(disciplinaAtualizada);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Recebida solicitação para excluir a disciplina com id: {}", id);
        service.excluir(id);
        log.info("Disciplina com id {} excluída com sucesso", id);
        return ResponseEntity.noContent().build();
    }
}
