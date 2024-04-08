package br.com.fullstackeducation.miniprojeto2.controller;

import br.com.fullstackeducation.miniprojeto2.entity.DisciplinaEntity;
import br.com.fullstackeducation.miniprojeto2.entity.ProfessorEntity;
import br.com.fullstackeducation.miniprojeto2.service.ProfessorServiceImpl;
import br.com.fullstackeducation.miniprojeto2.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/professores")
public class ProfessorController {

    private final ProfessorServiceImpl professorServiceImpl;

    public ProfessorController(ProfessorServiceImpl professorServiceImpl) {
        this.professorServiceImpl = professorServiceImpl;
    }
    @PostMapping
    public ResponseEntity<ProfessorEntity> criarProfessor(@RequestBody ProfessorEntity professor) {
        log.info("POST /Professores -> Início");
        log.info("POST /Professores -> Cadastrado");
        log.info("POST /Professores -> 201 CREATED");
        log.info("POST /Professores -> Response Body: \n{}\n", JsonUtil
                .objetoParaJson(professorServiceImpl.criarProfessor(professor)));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(professorServiceImpl.criarProfessor(professor));
    }
    @GetMapping
    public ResponseEntity<List<ProfessorEntity>> listarProfessores() {
        log.info("GET/Professores -> Início");
        log.info("GET/Professores -> Encontrados {} registros", professorServiceImpl.listarProfessores().size());
        log.info("GET/Professores -> 200 ok");
        log.info("GET/Professores -> Response Body: \n{}\n", JsonUtil
                .objetoParaJson(professorServiceImpl.listarProfessores()));
        return ResponseEntity.ok(professorServiceImpl.listarProfessores());
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProfessorEntity> buscarProfessorPorId(@PathVariable Long id) {
        log.info("GET /Professores/{} -> Início", id);
        log.info("GET /Professores/{} -> Encontrados", id);
        log.info("GET /Professores/{} -> 200 OK", id);
        log.debug("GET /Professores/{} -> Response Body:\n{}\n", id, JsonUtil
                .objetoParaJson(professorServiceImpl.buscarProfessorPorId(id)));
        return ResponseEntity.ok(professorServiceImpl.buscarProfessorPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProfessorEntity> atualizarProfessor(@PathVariable Long id, @RequestBody ProfessorEntity professor) {
        log.info("PUT /Professores/{}", id);
        log.info("PUT /Professores/{} -> Atualizado", id);
        log.info("PUT /Professores/{} -> 200 OK", id);
        log.debug("PUT /Professores/{} -> Response Body:\n{}\n", id, JsonUtil
                .objetoParaJson(professorServiceImpl.atualizarProfessor(id, professor)));
        return ResponseEntity.ok(professorServiceImpl.atualizarProfessor(id, professor));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProfessor(@PathVariable Long id) {
        log.info("DELETE /Professores/{}", id);
        log.info("DELETE /Professores/{} -> Excluído", id);
        log.info("DELETE /Professores/{} -> 204 No Content", id);
        professorServiceImpl.excluirProfessor(id);
        return ResponseEntity.noContent().build();
    }
}
