package br.com.fullstackeducation.miniprojeto2.service;

import br.com.fullstackeducation.miniprojeto2.entity.DisciplinaEntity;
import br.com.fullstackeducation.miniprojeto2.exception.NotFoundException;
import br.com.fullstackeducation.miniprojeto2.repository.DisciplinaRepository;
import br.com.fullstackeducation.miniprojeto2.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class DisciplinaServiceImpl implements DisciplinaService {


    private final DisciplinaRepository disciplinaRepository;

    public DisciplinaServiceImpl(DisciplinaRepository disciplinaRepository) {
        this.disciplinaRepository = disciplinaRepository;
    }

    public DisciplinaEntity criarDisciplina(DisciplinaEntity disciplinaNova) {
        disciplinaNova.setId(null);
        log.info("Criando disciplina -> Salvar: \n{}\n", JsonUtil.objetoParaJson(disciplinaNova));
        DisciplinaEntity disciplina = disciplinaRepository.save(disciplinaNova);
        log.info("Criando disciplina -> Salvo com sucesso");
        log.debug("Criando disciplina -> Registro Salvo: \n{}\n", JsonUtil.objetoParaJson(disciplinaNova));
        return disciplina;
    }

    public List<DisciplinaEntity> listarDisciplinas() {
        log.info("Buscando todas as disciplinas");
        List<DisciplinaEntity> disciplinas = disciplinaRepository.findAll();
        log.info("Buscando todas as disciplinas -> {} disciplinas encontradas", disciplinaRepository.findAll().size());
        log.debug("Buscando todas as disciplinas -> Registros encontrados:\n{}\n", JsonUtil.objetoParaJson(disciplinaRepository));
        return disciplinas;

    }

    public DisciplinaEntity buscarDisciplinaPorId(Long id) {
        log.info("Buscando disciplina por ID: {}", id);
        Optional<DisciplinaEntity> disciplina = disciplinaRepository.findById(id);
        if (disciplina.isEmpty()) {
            log.error("Buscando disciplina por id {} -> NÃO Encontrado", id);
            return disciplinaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Disciplina não encontrada com o ID: " + id));
        }
        log.info("Buscando disciplina por id ({}) -> Encontrado", id);
        log.debug("Buscando disciplina por id ({}) -> Registro encontrado:\n{}\n", id, JsonUtil.objetoParaJson(disciplina.get()));
        return disciplina.get();
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