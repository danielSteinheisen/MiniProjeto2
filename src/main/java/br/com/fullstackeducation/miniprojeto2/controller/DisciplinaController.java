package br.com.fullstackeducation.miniprojeto2.controller;

import br.com.fullstackeducation.miniprojeto2.entity.DisciplinaEntity;
import br.com.fullstackeducation.miniprojeto2.service.DisciplinaServiceImpl;
import br.com.fullstackeducation.miniprojeto2.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/disciplinas")
public class DisciplinaController {


    private final DisciplinaServiceImpl disciplinaServiceImpl;

    public DisciplinaController(DisciplinaServiceImpl disciplinaServiceImpl) {
        this.disciplinaServiceImpl = disciplinaServiceImpl;
    }

    @PostMapping
    public ResponseEntity<DisciplinaEntity> criarDisciplina(@RequestBody DisciplinaEntity disciplina) {
        log.info("POST /Disciplinas -> Início");
        log.info("POST /Disciplinas -> Cadastrada");
        log.info("POST /Disciplinas -> 201 CREATED");
        log.info("POST /Disciplinas -> Response Body: \n{}\n", JsonUtil
                .objetoParaJson(disciplinaServiceImpl.criarDisciplina(disciplina)));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(disciplinaServiceImpl.criarDisciplina(disciplina));
    }

    @GetMapping
    public ResponseEntity<List<DisciplinaEntity>> listarDisciplinas() {
        log.info("GET/disciplinas -> Início");
        log.info("GET/disciplinas -> Encontrados {} registros", disciplinaServiceImpl.listarDisciplinas().size());
        log.info("GET/disciplinas -> 200 ok");
        log.info("GET/disciplinas -> Response Body: \n{}\n", JsonUtil
                .objetoParaJson(disciplinaServiceImpl.listarDisciplinas()));
        return ResponseEntity.ok(disciplinaServiceImpl.listarDisciplinas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaEntity> buscarDisciplinaPorId(@PathVariable Long id) {
        log.info("GET /Disciplinas/{} -> Início", id);
        log.info("GET /Disciplinas/{} -> Encontradas", id);
        log.info("GET /Disciplinas/{} -> 200 OK", id);
        log.debug("GET /Disciplinas/{} -> Response Body:\n{}\n", id, JsonUtil
                .objetoParaJson(disciplinaServiceImpl.buscarDisciplinaPorId(id)));
        return ResponseEntity.ok(disciplinaServiceImpl.buscarDisciplinaPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplinaEntity> atualizarDisciplina(@PathVariable Long id, @RequestBody DisciplinaEntity disciplina) {
        log.info("PUT /Disciplinas/{}", id);
        log.info("PUT /Disciplinas/{} -> Atualizada", id);
        log.info("PUT /Disciplinas/{} -> 200 OK", id);
        log.debug("PUT /Disciplinas/{} -> Response Body:\n{}\n", id, JsonUtil
                .objetoParaJson(disciplinaServiceImpl.atualizarDisciplina(id, disciplina)));
        return ResponseEntity.ok(disciplinaServiceImpl.atualizarDisciplina(id, disciplina));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDisciplina(@PathVariable Long id) {
        log.info("DELETE /Disciplinas/{}", id);
        log.info("DELETE /Disciplinas/{} -> Excluída", id);
        log.info("DELETE /Disciplinas/{} -> 204 No Content", id);
        disciplinaServiceImpl.excluirDisciplina(id);
        return ResponseEntity.noContent().build();
    }
}