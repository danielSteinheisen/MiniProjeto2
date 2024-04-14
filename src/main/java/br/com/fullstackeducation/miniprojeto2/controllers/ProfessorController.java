package br.com.fullstackeducation.miniprojeto2.controllers;

import br.com.fullstackeducation.miniprojeto2.entities.ProfessorEntity;
import br.com.fullstackeducation.miniprojeto2.services.ProfessorService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("professores")
public class ProfessorController {

    private final ProfessorService service;

    @GetMapping
    public ResponseEntity<List<ProfessorEntity>> get() {
        log.info("Recebida solicitação para obter todos os professores");
        List<ProfessorEntity> professores = service.buscarTodos();
        log.info("Retornando todos os professores: {}", professores);
        return ResponseEntity.ok(professores);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProfessorEntity> getId(@PathVariable Long id) {
        log.info("Recebida solicitação para obter o professor com id: {}", id);
        ProfessorEntity professor = service.buscarPorId(id);
        log.info("Retornando o professor com id {}: {}", id, professor);
        return ResponseEntity.ok(professor);
    }

    @PostMapping
    public ResponseEntity<ProfessorEntity> post(@RequestBody ProfessorEntity request) {
        log.info("Recebida solicitação para criar um novo professor: {}", request);
        ProfessorEntity professorCriado = service.criar(request);
        log.info("Professor criado com sucesso: {}", professorCriado);
        return ResponseEntity.status(HttpStatus.CREATED).body(professorCriado);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProfessorEntity> put(@PathVariable Long id, @RequestBody ProfessorEntity request) {
        log.info("Recebida solicitação para atualizar o professor com id {}: {}", id, request);
        ProfessorEntity professorAtualizado = service.alterar(id, request);
        log.info("Professor atualizado com sucesso: {}", professorAtualizado);
        return ResponseEntity.status(HttpStatus.CREATED).body(professorAtualizado);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Recebida solicitação para excluir o professor com id: {}", id);
        service.excluir(id);
        log.info("Professor com id {} excluído com sucesso", id);
        return ResponseEntity.noContent().build();
    }
}
