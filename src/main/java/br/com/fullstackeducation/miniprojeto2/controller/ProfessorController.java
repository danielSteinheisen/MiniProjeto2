package br.com.fullstackeducation.miniprojeto2.controller;

import br.com.fullstackeducation.miniprojeto2.dto.ProfessorFiltro;
import br.com.fullstackeducation.miniprojeto2.entity.ProfessorEntity;
import br.com.fullstackeducation.miniprojeto2.service.ProfessorServiceImpl;
import br.com.fullstackeducation.miniprojeto2.util.JsonUtil;
import br.com.fullstackeducation.miniprojeto2.util.ParamUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/professores")
public class ProfessorController {

    private final ProfessorServiceImpl professorServiceImpl;

    @PostMapping
    public ResponseEntity<ProfessorEntity> criarProfessor(@RequestBody ProfessorEntity alunoRequisicao) {
        log.info("POST /professor -> Início");

        ProfessorEntity aluno = professorServiceImpl.criarProfessor(alunoRequisicao);
        log.info("POST /Professors -> Cadastrado");

        log.info("POST /Professors -> 201 CREATED");
        log.info("POST /Professors -> Response Body: \n{}\n", JsonUtil.objetoParaJson(aluno));
        return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
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
        log.info("GET /Professor/{} -> Início", id);
        log.info("GET /Professor/{} -> Encontrados", id);
        log.info("GET /Professor/{} -> 200 OK", id);
        log.debug("GET /Professor/{} -> Response Body:\n{}\n", id, JsonUtil
                .objetoParaJson(professorServiceImpl.buscarProfessorPorId(id)));
        return ResponseEntity.ok(professorServiceImpl.buscarProfessorPorId(id));
    }

    @GetMapping("nome/{nome}")
    public ResponseEntity<ProfessorEntity> buscarProfessorPorNome(@PathVariable String nome) {
        log.info("GET /professor/{} -> Início", nome);

        ProfessorEntity professor = professorServiceImpl.buscarProfessorPorNome(nome);
        log.info("GET /professor/{} -> Encontrado", nome);

        log.info("GET /professor/{} -> 200 OK", nome);
        log.debug("GET /professor/{} -> Response Body:\n{}\n", nome, JsonUtil.objetoParaJson(professor));
        return ResponseEntity.status(HttpStatus.OK).body(professor);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProfessorEntity> atualizarProfessor(@PathVariable Long id, @RequestBody ProfessorEntity alunoRequisicao) {
        log.info("PUT /professor/{}", id);

        ProfessorEntity professor = professorServiceImpl.atualizarProfessor(id, alunoRequisicao);
        log.info("PUT /professor/{} -> Atualizado", id);

        log.info("PUT /professor/{} -> 200 OK", id);
        log.debug("PUT /professor/{} -> Response Body:\n{}\n", id, JsonUtil.objetoParaJson(professor));
        return ResponseEntity.status(HttpStatus.OK).body(professor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProfessor(@PathVariable Long id) {
        log.info("DELETE /professor/{}", id);

        professorServiceImpl.excluirProfessor(id);
        log.info("DELETE /professor/{} -> Excluído", id);

        log.info("DELETE /professor/{} -> 204 No Content", id);

        return ResponseEntity.noContent().build();
    }
}
