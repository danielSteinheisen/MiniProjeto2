package br.com.fullstackeducation.miniprojeto2.controller;

import br.com.fullstackeducation.miniprojeto2.dto.AlunoFiltro;
import br.com.fullstackeducation.miniprojeto2.entity.AlunoEntity;
import br.com.fullstackeducation.miniprojeto2.service.AlunoServiceImpl;
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
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoServiceImpl alunoServiceImpl;

    @PostMapping
    public ResponseEntity<AlunoEntity> criarAluno(@RequestBody AlunoEntity aluno) {
        log.info("POST /Alunos -> Início");
        log.info("POST /Alunos -> Cadastrado");
        log.info("POST /Alunos -> 201 CREATED");
        log.info("POST /Alunos -> Response Body: \n{}\n", JsonUtil
                .objetoParaJson(alunoServiceImpl.criarAluno(aluno)));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(alunoServiceImpl.criarAluno(aluno));
    }

    @GetMapping
    public ResponseEntity<List<AlunoEntity>> get(AlunoFiltro filtro) {
        String queryParams = ParamUtil.objetoParaQueryParam(filtro);
        log.info("GET /alunos{} -> Início", queryParams);

        List<AlunoEntity> alunos = alunoServiceImpl.listarAlunos(filtro);
        log.info("GET /alunos{} -> Encontrados {} registros", queryParams, alunos.size());

        log.info("GET /alunos -> 200 ok", queryParams);
        log.debug("GET /alunos -> Response Body: \n{}\n", queryParams, JsonUtil.objetoParaJson(alunos));

        return ResponseEntity.status(HttpStatus.OK).body(alunos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoEntity> buscarAlunoPorId(@PathVariable Long id) {
        log.info("GET /Alunos/{} -> Início", id);
        log.info("GET /Alunos/{} -> Encontrados", id);
        log.info("GET /Alunos/{} -> 200 OK", id);
        log.debug("GET /Alunos/{} -> Response Body:\n{}\n", id, JsonUtil
                .objetoParaJson(alunoServiceImpl.buscarAlunoPorId(id)));
        return ResponseEntity.ok(alunoServiceImpl.buscarAlunoPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoEntity> atualizarAluno(@PathVariable Long id, @RequestBody AlunoEntity aluno) {
        log.info("PUT /Alunos/{}", id);
        log.info("PUT /Alunos/{} -> Atualizado", id);
        log.info("PUT /Alunos/{} -> 200 OK", id);
        log.debug("PUT /Alunos/{} -> Response Body:\n{}\n", id, JsonUtil
                .objetoParaJson(alunoServiceImpl.atualizarAluno(id, aluno)));
        return ResponseEntity.ok(alunoServiceImpl.atualizarAluno(id, aluno));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAluno(@PathVariable Long id) {
        log.info("DELETE /Alunos/{}", id);
        log.info("DELETE /Alunos/{} -> Excluído", id);
        log.info("DELETE /Alunos/{} -> 204 No Content", id);
        alunoServiceImpl.excluirAluno(id);
        return ResponseEntity.noContent().build();
    }
}