package br.com.fullstackeducation.miniprojeto2.service;

import br.com.fullstackeducation.miniprojeto2.entity.DisciplinaEntity;
import br.com.fullstackeducation.miniprojeto2.exception.NotFoundException;
import br.com.fullstackeducation.miniprojeto2.repository.DisciplinaRepository;
import br.com.fullstackeducation.miniprojeto2.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class DisciplinaService {


    private final DisciplinaRepository disciplinaRepository;

    public DisciplinaService(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    public DisciplinaEntity criarDisciplina(DisciplinaEntity disciplina) {
        log.info("Criando disciplina -> Salvar: \n{}\n", JsonUtil.objetoParaJson(disciplina));
        log.info("Criando disciplina -> Salvo com sucesso");
        log.debug("Criando disciplina -> Registro Salvo: \n{}\n", JsonUtil.objetoParaJson(disciplina));
        return disciplinaRepository.save(disciplina);
    }

    public List<DisciplinaEntity> listarDisciplinas() {
        log.info("Buscando todas as disciplinas");
        log.info("Buscando todas as disciplinas -> {} disciplinas encontradas", disciplinaRepository.findAll().size());
        log.debug("Buscando todas as disciplinas -> Registros encontrados:\n{}\n", JsonUtil.objetoParaJson(disciplinaRepository));
        return disciplinaRepository.findAll();

    }

    public DisciplinaEntity buscarDisciplinaPorId(Long id) {
        log.info("Buscando disciplina por ID: {}", id);
        log.error("Buscando livro por id {} -> NÃO Encontrado", id);
        return disciplinaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Disciplina não encontrada com o ID: " + id));
    }

    public DisciplinaEntity atualizarDisciplina(Long id, DisciplinaEntity disciplina) {
        if (!disciplinaRepository.existsById(id)) {
            log.info("Alterando disciplina com id ({}) -> Salvar: \n{}\n", id, JsonUtil.objetoParaJson(disciplina));
            throw new NotFoundException("Disciplina não encontrada com o ID: " + id);
        }
        disciplina.setId(id);
        log.info("Alterando disciplina -> Salvo com sucesso");
        log.debug("Alterando disciplina -> Registro Salvo: \n{}\n", JsonUtil.objetoParaJson(disciplina));
        return disciplinaRepository.save(disciplina);
    }

    public void excluirDisciplina(Long id) {
        if (!disciplinaRepository.existsById(id)) {
            log.info("Excluindo disciplina com id ({}) -> Excluindo", id);
            throw new NotFoundException("Disciplina não encontrada com o ID: " + id);
        }
        disciplinaRepository.deleteById(id);
        log.info("Excluindo disciplina com id ({}) -> Excluído com sucesso", id);
    }
}