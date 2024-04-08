package br.com.fullstackeducation.miniprojeto2.controller;

import br.com.fullstackeducation.miniprojeto2.dto.DisciplinaFiltro;
import br.com.fullstackeducation.miniprojeto2.entity.DisciplinaEntity;
import br.com.fullstackeducation.miniprojeto2.service.DisciplinaServiceImpl;
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
@RequestMapping("/disciplinas")
public class DisciplinaController {

    private final DisciplinaServiceImpl disciplinaServiceImpl;

    @PostMapping
    public ResponseEntity<DisciplinaEntity> criarDisciplina(@RequestBody DisciplinaEntity disciplinaRequisicao) {
        log.info("POST /Disciplinas -> Início");

        DisciplinaEntity disciplina = disciplinaServiceImpl.criarDisciplina(disciplinaRequisicao);
        log.info("POST /Disciplinas -> Cadastrada");

        log.info("POST /Disciplinas -> 201 CREATED");
        log.info("POST /Disciplinas -> Response Body: \n{}\n", JsonUtil.objetoParaJson(disciplina));
        return ResponseEntity.status(HttpStatus.CREATED).body(disciplina);
    }

    @GetMapping
    public ResponseEntity<List<DisciplinaEntity>> listarDisciplinas(DisciplinaFiltro filtro) {
        String queryParams = ParamUtil.objetoParaQueryParam(filtro);
        log.info("GET/disciplinas -> Início", queryParams);

        List<DisciplinaEntity> disciplinas = disciplinaServiceImpl.listarDisciplinas(filtro);
        log.info("GET/disciplinas -> Encontrados {} registros", queryParams, disciplinas.size());

        log.info("GET/disciplinas -> 200 ok", queryParams);
        log.info("GET/disciplinas -> Response Body: \n{}\n", JsonUtil.objetoParaJson(disciplinas));
        return ResponseEntity.status(HttpStatus.OK).body(disciplinas);
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

    @GetMapping("nome/{nome}")
    public ResponseEntity<DisciplinaEntity> buscarDisciplinaPorNome(@PathVariable String nome) {
        log.info("GET /disciplina/{} -> Início", nome);

        DisciplinaEntity disciplina = disciplinaServiceImpl.buscarDisciplinaPorNome(nome);
        log.info("GET /disciplina/{} -> Encontrado", nome);

        log.info("GET /disciplina/{} -> 200 OK", nome);
        log.debug("GET /disciplina/{} -> Response Body:\n{}\n", nome, JsonUtil.objetoParaJson(disciplina));
        return ResponseEntity.status(HttpStatus.OK).body(disciplina);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplinaEntity> atualizarDisciplina(@PathVariable Long id, @RequestBody DisciplinaEntity disciplinaRequisicao) {
        log.info("PUT /Disciplinas/{}", id);

        DisciplinaEntity disciplina = disciplinaServiceImpl.atualizarDisciplina(id, disciplinaRequisicao);
        log.info("PUT /Disciplinas/{} -> Atualizada", id);

        log.info("PUT /Disciplinas/{} -> 200 OK", id);
        log.debug("PUT /Disciplinas/{} -> Response Body:\n{}\n", id, JsonUtil.objetoParaJson(disciplina));
        return ResponseEntity.status(HttpStatus.OK).body(disciplina);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirDisciplina(@PathVariable Long id) {
        log.info("DELETE /Disciplinas/{}", id);

        disciplinaServiceImpl.excluirDisciplina(id);
        log.info("DELETE /Disciplinas/{} -> Excluída", id);

        log.info("DELETE /Disciplinas/{} -> 204 No Content", id);

        return ResponseEntity.noContent().build();
    }
}

