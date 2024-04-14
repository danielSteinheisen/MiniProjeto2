package br.com.fullstackeducation.miniprojeto2.controllers;

import br.com.fullstackeducation.miniprojeto2.entities.AlunoEntity;
import br.com.fullstackeducation.miniprojeto2.services.AlunoService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("alunos")
public class AlunoController {

    private final AlunoService service;

    @GetMapping
    public ResponseEntity<List<AlunoEntity>> get() {
        log.info("Recebida solicitação para obter todos os alunos");
        List<AlunoEntity> alunos = service.buscarTodos();
        log.info("Encontrados {} alunos", alunos.size());
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("{id}")
    public ResponseEntity<AlunoEntity> getId(@PathVariable Long id) {
        log.info("Recebida solicitação para obter o aluno pelo id: {}", id);
        AlunoEntity aluno = service.buscarPorId(id);
        log.info("Retornando o aluno com id {}: {}", id, aluno);
        return ResponseEntity.ok(aluno);
    }

    @PostMapping
    public ResponseEntity<AlunoEntity> post(@RequestBody AlunoEntity request) {
        log.info("Recebida solicitação para criar um novo aluno: {}", request);
        AlunoEntity alunoCriado = service.criar(request);
        log.info("Aluno criado com sucesso: {}", alunoCriado);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoCriado);
    }

    @PutMapping("{id}")
    public ResponseEntity<AlunoEntity> put(@PathVariable Long id, @RequestBody AlunoEntity request) {
        log.info("Recebida solicitação para alterar o aluno pelo id: {} para: {}", id, request);
        AlunoEntity alunoAtualizado = service.alterar(id, request);
        log.info("Aluno atualizado com sucesso: {}", alunoAtualizado);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoAtualizado);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.info("Recebida solicitação para excluir o aluno pelo id: {}", id);
        service.excluir(id);
        log.info("Aluno com id {} excluído com sucesso", id);
        return ResponseEntity.noContent().build();
    }
}
