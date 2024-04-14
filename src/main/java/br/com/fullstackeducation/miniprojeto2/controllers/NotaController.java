package br.com.fullstackeducation.miniprojeto2.controllers;

import br.com.fullstackeducation.miniprojeto2.entities.NotaEntity;
import br.com.fullstackeducation.miniprojeto2.services.NotaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("notas")
public class NotaController {

    private final NotaService service;

    @GetMapping
    public ResponseEntity<List<NotaEntity>> get() {
        log.info("Recebida solicitação para obter todas as notas");
        List<NotaEntity> notas = service.buscarTodos();
        log.info("Retornando todas as notas: {}", notas);
        return ResponseEntity.ok(notas);
    }

    @GetMapping("{id}")
    public ResponseEntity<NotaEntity> getId(@PathVariable Long id) {
        log.info("Recebida solicitação para obter a nota com id: {}", id);
        NotaEntity nota = service.buscarPorId(id);
        log.info("Retornando a nota com id {}: {}", id, nota);
        return ResponseEntity.ok(nota);
    }

    @GetMapping("matricula-id/{matriculaId}")
    public ResponseEntity<List<NotaEntity>> getMatriculaId(@PathVariable Long matriculaId) {
        log.info("Recebida solicitação para obter as notas da matrícula com id: {}", matriculaId);
        List<NotaEntity> notas = service.buscarPorMatricula(matriculaId);
        log.info("Retornando as notas da matrícula com id {}: {}", matriculaId, notas);
        return ResponseEntity.ok(notas);
    }

    @PostMapping
    public ResponseEntity<NotaEntity> post(@RequestBody NotaEntity request) {
        log.info("Recebida solicitação para criar uma nova nota: {}", request);
        NotaEntity novaNota = service.criar(request);
        log.info("Nova nota criada com sucesso: {}", novaNota);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaNota);
    }

    @PutMapping("{id}")
    public ResponseEntity<NotaEntity> put(@PathVariable Long id, @RequestBody NotaEntity request) {
        log.info("Recebida solicitação para atualizar a nota com id {}: {}", id, request);
        NotaEntity notaAtualizada = service.alterar(id, request);
        log.info("Nota atualizada com sucesso: {}", notaAtualizada);
        return ResponseEntity.status(HttpStatus.CREATED).body(notaAtualizada);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Recebida solicitação para excluir a nota com id: {}", id);
        service.excluir(id);
        log.info("Nota com id {} excluída com sucesso", id);
        return ResponseEntity.noContent().build();
    }
}
