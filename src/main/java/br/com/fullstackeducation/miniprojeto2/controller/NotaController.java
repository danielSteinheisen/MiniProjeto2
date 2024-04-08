package br.com.fullstackeducation.miniprojeto2.controller;

import br.com.fullstackeducation.miniprojeto2.entity.MatriculaEntity;
import br.com.fullstackeducation.miniprojeto2.entity.NotaEntity;
import br.com.fullstackeducation.miniprojeto2.service.NotaServiceImpl;
import br.com.fullstackeducation.miniprojeto2.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/notas")
public class NotaController {

    private final NotaServiceImpl notaServiceImpl;

    public NotaController(NotaServiceImpl notaServiceImpl) {
        this.notaServiceImpl = notaServiceImpl;
    }
    @PostMapping
    public ResponseEntity<NotaEntity> criarNota(@RequestBody NotaEntity nota) {
        log.info("POST /Nota -> Início");
        log.info("POST /Nota -> Cadastrada");
        log.info("POST /Nota -> 201 CREATED");
        log.info("POST /Nota -> Response Body: \n{}\n", JsonUtil
                .objetoParaJson(notaServiceImpl.criarNota(nota)));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(notaServiceImpl.criarNota(nota));
    }
    @GetMapping
    public ResponseEntity<List<NotaEntity>> listarNotas() {
        log.info("GET/Notas -> Início");
        log.info("GET/Notas -> Encontrados {} registros", notaServiceImpl.listarNotas().size());
        log.info("GET/Notas -> 200 ok");
        log.info("GET/Notas -> Response Body: \n{}\n", JsonUtil
                .objetoParaJson(notaServiceImpl.listarNotas()));
        return ResponseEntity.ok(notaServiceImpl.listarNotas());
    }
    @GetMapping("/{id}")
    public ResponseEntity<NotaEntity> buscarNotaPorId(@PathVariable Long id) {
        log.info("GET /Notas/{} -> Início", id);
        log.info("GET /Notas/{} -> Encontradas", id);
        log.info("GET /Notas/{} -> 200 OK", id);
        log.debug("GET /Notas/{} -> Response Body:\n{}\n", id, JsonUtil
                .objetoParaJson(notaServiceImpl.buscarNotaPorId(id)));
        return ResponseEntity.ok(notaServiceImpl.buscarNotaPorId(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<NotaEntity> atualizarNota(@PathVariable Long id, @RequestBody NotaEntity nota) {
        log.info("PUT /Notas/{}", id);
        log.info("PUT /Notas/{} -> Atualizada", id);
        log.info("PUT /Notas/{} -> 200 OK", id);
        log.debug("PUT /Notas/{} -> Response Body:\n{}\n", id, JsonUtil
                .objetoParaJson(notaServiceImpl.atualizarNota(id, nota)));
        return ResponseEntity.ok(notaServiceImpl.atualizarNota(id, nota));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirNota(@PathVariable Long id) {
        log.info("DELETE /Notas/{}", id);
        log.info("DELETE /Notas/{} -> Excluída", id);
        log.info("DELETE /Notas/{} -> 204 No Content", id);
        notaServiceImpl.excluirNota(id);
        return ResponseEntity.noContent().build();
    }
}
