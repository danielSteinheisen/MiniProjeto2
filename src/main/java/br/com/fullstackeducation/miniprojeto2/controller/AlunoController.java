package br.com.fullstackeducation.miniprojeto2.controller;

import br.com.fullstackeducation.miniprojeto2.dto.AlunoFiltro;
import br.com.fullstackeducation.miniprojeto2.entity.AlunoEntity;
import br.com.fullstackeducation.miniprojeto2.service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aluno")
@RequiredArgsConstructor
public class AlunoController {

    private final AlunoService service;

    @GetMapping
    public ResponseEntity<List<AlunoEntity>> get(AlunoFiltro filtro) {
        List<AlunoEntity> alunos = service.buscarTodos(filtro);
        return ResponseEntity.status(HttpStatus.OK).body(alunos);
    }

    @GetMapping("{id}")
    public AlunoEntity getId(@PathVariable Long id) throws Exception {
        return service.buscarPorId(id);
    }

    @GetMapping("nome/{nome}")
    public AlunoEntity getId(@PathVariable String nome) throws Exception {
        return service.buscarPorNome(nome);
    }

    @PostMapping
    public ResponseEntity<AlunoEntity> post(@RequestBody AlunoEntity aluno) throws Exception {
        AlunoEntity alunoCriado = service.criar(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(alunoCriado);
    }

    @PutMapping("{id}")
    public AlunoEntity put(@PathVariable Long id, @RequestBody AlunoEntity aluno) throws Exception {
        return service.alterar(id, aluno);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id) throws Exception {
        service.apagar(id);
    }
}
