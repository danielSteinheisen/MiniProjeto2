package br.com.fullstackeducation.miniprojeto2.controller;

import br.com.fullstackeducation.miniprojeto2.dto.ProfessorFiltro;
import br.com.fullstackeducation.miniprojeto2.entity.DisciplinaEntity;
import br.com.fullstackeducation.miniprojeto2.entity.ProfessorEntity;
import br.com.fullstackeducation.miniprojeto2.service.ProfessorServiceImpl;
import br.com.fullstackeducation.miniprojeto2.util.JsonUtil;
import br.com.fullstackeducation.miniprojeto2.util.ParamUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/professores")
public class ProfessorController {

    private final ProfessorServiceImpl professorServiceImpl;

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
    public ResponseEntity<List<ProfessorEntity>> get(ProfessorFiltro filtro) {
        String queryParams = ParamUtil.objetoParaQueryParam(filtro);
        log.info("GET /professor{} -> Início", queryParams);

        List<ProfessorEntity> professor = professorServiceImpl.listarProfessores(filtro);
        log.info("GET /professor{} -> Encontrados {} registros", queryParams, professor.size());

        log.info("GET /professor -> 200 ok", queryParams);
        log.debug("GET /professor -> Response Body: \n{}\n", queryParams, JsonUtil.objetoParaJson(professor));

        return ResponseEntity.status(HttpStatus.OK).body(professor);
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
